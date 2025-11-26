package io.cucumber.scalatest

import io.cucumber.core.eventbus.EventBus
import io.cucumber.core.feature.FeatureParser
import io.cucumber.core.filter.Filters
import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.logging.LoggerFactory
import io.cucumber.core.options._
import io.cucumber.core.plugin.{PluginFactory, Plugins}
import io.cucumber.core.resource.ClassLoaders
import io.cucumber.core.runtime._
import org.scalatest.{Args, FailedStatus, Status, Suite}

import java.time.Clock
import java.util.function.{Predicate, Supplier}
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success, Try}

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
  *     plugin = List("pretty"),
  *     tags = Some("@foo and @bar")
  *   )
  * }
  * }}}
  */
trait CucumberSuite extends Suite {

  private val LOGGER = LoggerFactory.getLogger(classOf[CucumberSuite])

  /** Override this value to configure Cucumber options
    */
  def cucumberOptions: CucumberOptions

  private lazy val (bus, plugins, context, children) = initialize()

  override def nestedSuites: scala.collection.immutable.IndexedSeq[Suite] = children.toIndexedSeq

  override def run(testName: Option[String], args: Args): Status = {
    if (testName.isDefined) {
      throw new IllegalArgumentException("Running a single test by name is not supported in CucumberSuite")
    }

    // Note:
    // Could use plugins.setSerialEventBusOnEventListenerPlugins(bus) if multithreading assumed
    // This was used in JUnit 4 implementation in case setScheduler was called
    plugins.setEventBusOnEventListenerPlugins(bus)

    var status: Status = FailedStatus
    Try {
      context.runFeatures { () =>
        status = super.run(testName, args)
        println("Cucumber Suite " + status.succeeds())
        ()
      }
    } match {
      case Success(_) =>
        status
      case Failure(exception) =>
        LOGGER.error(exception, () => "Error when running features")
        // TODO: do something with the exception?
        FailedStatus
    }
  }

  private def initialize(): (EventBus, Plugins, CucumberExecutionContext, Seq[FeatureSuite]) = {
    val runtimeOptions = buildRuntimeOptions()

    val classLoaderSupplier: Supplier[ClassLoader] = () => ClassLoaders.getDefaultClassLoader()
    val uuidGeneratorServiceLoader = new UuidGeneratorServiceLoader(classLoaderSupplier, runtimeOptions)
    val bus: EventBus = SynchronizedEventBus.synchronize(
      new TimeServiceEventBus(Clock.systemUTC(), uuidGeneratorServiceLoader.loadUuidGenerator())
    )

    // Parse the features early. Don't proceed when there are lexer errors
    val parser = new FeatureParser(() => bus.generateId())
    val featureSupplier =
      new FeaturePathFeatureSupplier(classLoaderSupplier, runtimeOptions, parser)
    val features: Seq[Feature] = featureSupplier.get().asScala.toSeq

    // Create plugins after feature parsing to avoid the creation of empty files on lexer errors.
    val plugins = new Plugins(new PluginFactory(), runtimeOptions)
    val exitStatus = new ExitStatus(runtimeOptions)
    plugins.addPlugin(exitStatus)

    val objectFactoryServiceLoader = new ObjectFactoryServiceLoader(classLoaderSupplier, runtimeOptions)
    val objectFactorySupplier = new ThreadLocalObjectFactorySupplier(objectFactoryServiceLoader)
    val backendSupplier = new BackendServiceLoader(() => this.getClass.getClassLoader, objectFactorySupplier)
    val runnerSupplier = new ThreadLocalRunnerSupplier(runtimeOptions, bus, backendSupplier, objectFactorySupplier)

    val context = new CucumberExecutionContext(bus, exitStatus, runnerSupplier)
    val filters: Predicate[Pickle] = new Filters(runtimeOptions)

    val children: Seq[FeatureSuite] = features
      .map { feature => new FeatureSuite(feature, context, filters) }
      .filter(featureSuite => !featureSuite.isEmpty())
    // TODO suffix? tester same name
    // TODO test outline examples

    (bus, plugins, context, children)
  }

  private def buildRuntimeOptions(): RuntimeOptions = {
    val propertiesFileOptions = new CucumberPropertiesParser()
      .parse(CucumberProperties.fromPropertiesFile())
      .build()

    val suiteOptions = CucumberOptionsParser
      .parse(cucumberOptions, this.getClass)
      .build(propertiesFileOptions)

    val environmentOptions = new CucumberPropertiesParser()
      .parse(CucumberProperties.fromEnvironment())
      .build(suiteOptions)

    val runtimeOptions = new CucumberPropertiesParser()
      .parse(CucumberProperties.fromSystemProperties())
      .enablePublishPlugin()
      .build(environmentOptions)

    runtimeOptions
  }

}
