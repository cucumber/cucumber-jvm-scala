package io.cucumber.scalatest

import io.cucumber.core.feature.FeatureParser
import io.cucumber.core.filter.Filters
import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.options._
import io.cucumber.core.plugin.{PluginFactory, Plugins}
import io.cucumber.core.runtime._
import org.scalatest.{Args, Status, Suite}

import java.time.Clock
import java.util.function.{Predicate, Supplier}
import scala.jdk.CollectionConverters._
import scala.annotation.nowarn

/** Configuration for Cucumber tests.
  *
  * @param features
  *   paths to feature files or directories (e.g., "classpath:features")
  * @param glue
  *   packages containing step definitions (e.g., "com.example.steps")
  * @param plugin
  *   plugins to use (e.g., "pretty", "json:target/cucumber.json")
  * @param tags
  *   tag expression to filter scenarios (e.g., "@foo or @bar", "not @wip")
  */
case class CucumberOptions(
    features: List[String] = List.empty,
    glue: List[String] = List.empty,
    plugin: List[String] = List.empty,
    tags: Option[String] = None
)

/** A trait that allows Cucumber scenarios to be run with ScalaTest.
  *
  * Mix this trait into your test class and define the `cucumberOptions` value
  * to configure the Cucumber runtime.
  *
  * Options can be configured via:
  *   - The `cucumberOptions` value (programmatic configuration, takes
  *     precedence)
  *   - cucumber.properties file on the classpath
  *   - Environment variables starting with CUCUMBER_
  *   - System properties starting with cucumber.
  *
  * Each feature file appears as a nested suite, and each scenario within a
  * feature appears as a test within that suite.
  *
  * Example:
  * {{{
  * import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}
  *
  * class RunCucumberTest extends CucumberSuite {
  *   override val cucumberOptions = CucumberOptions(
  *     features = List("classpath:features"),
  *     glue = List("com.example.stepdefinitions"),
  *     plugin = List("pretty")
  *   )
  * }
  * }}}
  */
@nowarn
trait CucumberSuite extends Suite {

  /** Override this value to configure Cucumber options. If not overridden,
    * defaults will be used based on the package name.
    */
  def cucumberOptions: CucumberOptions = CucumberOptions()

  private lazy val classLoader: ClassLoader = getClass.getClassLoader

  private lazy val (features, context, filters) = {
    val runtimeOptions = buildRuntimeOptions()
    val classLoaderSupplier = new Supplier[ClassLoader] {
      override def get(): ClassLoader = classLoader
    }

    val uuidGeneratorServiceLoader =
      new UuidGeneratorServiceLoader(classLoaderSupplier, runtimeOptions)
    val bus = SynchronizedEventBus.synchronize(
      new TimeServiceEventBus(
        Clock.systemUTC(),
        uuidGeneratorServiceLoader.loadUuidGenerator()
      )
    )

    val parser = new FeatureParser(bus.generateId _)
    val featureSupplier =
      new FeaturePathFeatureSupplier(
        classLoaderSupplier,
        runtimeOptions,
        parser
      )
    val features = featureSupplier.get().asScala.toList

    val plugins = new Plugins(new PluginFactory(), runtimeOptions)
    val exitStatus = new ExitStatus(runtimeOptions)
    plugins.addPlugin(exitStatus)

    val objectFactoryServiceLoader =
      new ObjectFactoryServiceLoader(classLoaderSupplier, runtimeOptions)
    val objectFactorySupplier =
      new ThreadLocalObjectFactorySupplier(objectFactoryServiceLoader)
    val backendSupplier =
      new BackendServiceLoader(classLoaderSupplier, objectFactorySupplier)
    val runnerSupplier = new ThreadLocalRunnerSupplier(
      runtimeOptions,
      bus,
      backendSupplier,
      objectFactorySupplier
    )

    val context =
      new CucumberExecutionContext(bus, exitStatus, runnerSupplier)
    val filters: Predicate[Pickle] = new Filters(runtimeOptions)

    plugins.setEventBusOnEventListenerPlugins(bus)

    (features, context, filters)
  }

  override def nestedSuites: collection.immutable.IndexedSeq[Suite] = {
    features
      .map(feature => new FeatureSuite(feature, context, filters))
      .toIndexedSeq
  }

  override def run(testName: Option[String], args: Args): Status = {
    if (testName.isDefined) {
      throw new IllegalArgumentException(
        "Running a single test by name is not supported in CucumberSuite"
      )
    }
    var status: Status = org.scalatest.SucceededStatus
    try {
      context.runFeatures(() => {
        println(s"About to call super.run")
        status = super.run(testName, args)
        println(s"super.run returned status: $status, succeeds=${status.succeeds()}")
      })
    } catch {
      case ex: Throwable =>
        println(s"CucumberSuite.run caught exception: ${ex.getClass.getName}: ${ex.getMessage}")
        throw ex
    }
    println(s"CucumberSuite.run returning status: $status, succeeds=${status.succeeds()}")
    status
  }

  private def buildRuntimeOptions(): RuntimeOptions = {
    val packageName = getClass.getPackage.getName

    // Parse options from different sources in order of precedence
    val propertiesFileOptions = new CucumberPropertiesParser()
      .parse(CucumberProperties.fromPropertiesFile())
      .build()

    val annotationOptions = buildProgrammaticOptions(propertiesFileOptions)

    val environmentOptions = new CucumberPropertiesParser()
      .parse(CucumberProperties.fromEnvironment())
      .build(annotationOptions)

    val runtimeOptions = new CucumberPropertiesParser()
      .parse(CucumberProperties.fromSystemProperties())
      .build(environmentOptions)

    runtimeOptions
  }

  private def buildProgrammaticOptions(
      base: RuntimeOptions
  ): RuntimeOptions = {
    val packageName = getClass.getPackage.getName
    val builder = new RuntimeOptionsBuilder()

    // Add features (programmatic options take precedence)
    val features =
      if (cucumberOptions.features.nonEmpty) cucumberOptions.features
      else List("classpath:" + packageName.replace('.', '/'))

    features.foreach { feature =>
      builder.addFeature(
        io.cucumber.core.feature.FeatureWithLines.parse(feature)
      )
    }

    // Add glue
    val glue =
      if (cucumberOptions.glue.nonEmpty) cucumberOptions.glue
      else List(packageName)

    glue.foreach { g =>
      builder.addGlue(java.net.URI.create("classpath:" + g))
    }

    // Add plugins
    cucumberOptions.plugin.foreach { p =>
      builder.addPluginName(p)
    }

    // Add tags filter if specified
    cucumberOptions.tags.foreach { tagExpression =>
      builder.addTagFilter(
        io.cucumber.tagexpressions.TagExpressionParser.parse(tagExpression)
      )
    }

    builder.build(base)
  }

  private class FeatureSuite(
      feature: Feature,
      context: CucumberExecutionContext,
      filters: Predicate[Pickle]
  ) extends Suite {

    override def suiteName: String =
      feature.getName.orElse("EMPTY_NAME")

    override def nestedSuites: collection.immutable.IndexedSeq[Suite] = {
      feature
        .getPickles()
        .asScala
        .filter(filters.test)
        .map(pickle => new PickleSuite(feature, pickle, context))
        .toIndexedSeq
    }

    override def run(testName: Option[String], args: Args): Status = {
      println(s"FeatureSuite.run called")
      context.beforeFeature(feature)
      try {
        val status = super.run(testName, args)
        println(s"FeatureSuite.run returning status: $status, succeeds=${status.succeeds()}")
        status
      } catch {
        case ex: Throwable =>
          println(s"FeatureSuite.run caught exception: ${ex.getClass.getName}: ${ex.getMessage}")
          throw ex
      }
    }
  }

  private class PickleSuite(
      feature: Feature,
      pickle: Pickle,
      context: CucumberExecutionContext
  ) extends Suite {

    override def suiteName: String = pickle.getName

    override def testNames: Set[String] = Set("scenario")

    override def run(testName: Option[String], args: Args): Status = {
      var testFailed: Option[Throwable] = None

      // Execute the test
      context.runTestCase(runner => {
        // Subscribe to TestCaseFinished events to detect failures
        val handler = new io.cucumber.plugin.event.EventHandler[io.cucumber.plugin.event.TestCaseFinished] {
          override def receive(event: io.cucumber.plugin.event.TestCaseFinished): Unit = {
            val result = event.getResult
            if (!result.getStatus.isOk) {
              val error = result.getError
              if (error != null) {
                testFailed = Some(error)
              } else {
                testFailed = Some(new RuntimeException(s"Test failed with status: ${result.getStatus}"))
              }
            }
          }
        }
        runner.getBus.registerHandlerFor(classOf[io.cucumber.plugin.event.TestCaseFinished], handler)
        
        runner.runPickle(pickle)
      })

      testFailed match {
        case Some(ex) => 
          println(s"PickleSuite.run returning FailedStatus for: ${ex.getMessage}")
          org.scalatest.FailedStatus
        case None     => 
          println(s"PickleSuite.run returning SucceededStatus")
          org.scalatest.SucceededStatus
      }
    }
  }
}
