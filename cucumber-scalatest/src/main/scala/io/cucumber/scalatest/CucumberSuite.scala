package io.cucumber.scalatest

import io.cucumber.core.eventbus.EventBus
import io.cucumber.core.feature.FeatureParser
import io.cucumber.core.filter.Filters
import io.cucumber.core.gherkin.Feature
import io.cucumber.core.options.{
  Constants,
  CucumberProperties,
  CucumberPropertiesParser,
  RuntimeOptions
}
import io.cucumber.core.plugin.{PluginFactory, Plugins}
import io.cucumber.core.runtime._
import org.scalatest.{Args, Status, Suite}

import java.time.Clock
import java.util.function.Supplier
import scala.jdk.CollectionConverters._
import scala.collection.immutable.IndexedSeq
import scala.util.{Failure, Try}

/** Runs Cucumber features as a ScalaTest suite.
  *
  * The Cucumber model is mapped on ScalaTest's recursive suites:
  *
  * {{{
  * CucumberSuite        the run (your class)
  *  └─ feature          one nested suite per feature file
  *      └─ scenario     one nested suite per scenario (or outline example)
  *          └─ step     one test per step
  * }}}
  *
  * so that reports (sbt, IDEs) point at the exact step that failed. A failing,
  * undefined, pending or ambiguous step is reported as a failed test, which
  * fails its scenario, its feature and the whole suite. Steps that are skipped
  * because of a previous failure are reported as canceled. Failing hooks are
  * reported as tests named after the hook type.
  *
  * Configuration is read from `cucumber.properties`, the programmatic
  * [[cucumberOptions]], `CUCUMBER_*` environment variables and `cucumber.*`
  * system properties (from lowest to highest precedence).
  *
  * {{{
  * class RunCucumberTest extends CucumberSuite {
  *   override def cucumberOptions = CucumberOptions(
  *     features = Seq("classpath:features"),
  *     glue = Seq("com.example.steps"),
  *     plugin = Seq("pretty")
  *   )
  * }
  * }}}
  *
  * Scenarios are executed sequentially, whatever the ScalaTest distributor.
  * ScalaTest test name and tag filters do not apply, use the Cucumber
  * `cucumber.filter.tags` and `cucumber.filter.name` options instead.
  */
trait CucumberSuite extends Suite {

  /** Cucumber configuration. Defaults to the features and glue found in the
    * package of the concrete suite.
    */
  def cucumberOptions: CucumberOptions = CucumberOptions()

  // Only what is needed to list the features, so that discovering the suites
  // has no side effect (plugins, which may write files, are created in `run`).
  private[scalatest] lazy val setup: CucumberSuite.Setup =
    CucumberSuite.Setup(getClass, cucumberOptions)

  override def suiteName: String = getClass.getSimpleName

  override def testNames: Set[String] = Set.empty

  override def nestedSuites: IndexedSeq[Suite] =
    setup.features
      .map(feature => new FeatureSuite(feature, setup.filters, setup.context))
      .filter(_.pickles.nonEmpty)
      .toIndexedSeq

  override def run(testName: Option[String], args: Args): Status = {
    if (testName.isDefined)
      throw new IllegalArgumentException(
        "CucumberSuite does not support running a single test by name"
      )
    val context = setup.context
    setup.startPlugins()
    context.startTestRun()
    // Hooks and the end of the run must happen whatever the outcome of the
    // scenarios. The first error is thrown, the others are attached to it.
    val scenarios = Try {
      context.runBeforeAllHooks()
      super.run(None, args)
    }
    val teardown = Seq(
      Try(context.runAfterAllHooks()),
      Try(context.finishTestRun())
    )
    val errors = (scenarios +: teardown).collect { case Failure(t) => t }
    errors.headOption.foreach { first =>
      errors.tail.foreach(first.addSuppressed)
      throw first
    }
    scenarios.get
  }

  override protected def runNestedSuites(args: Args): Status =
    NestedSuites.runSequentially(this, args, level = 0)
}

private[scalatest] object CucumberSuite {

  final class Setup(
      val options: RuntimeOptions,
      val bus: EventBus,
      val features: List[Feature],
      val context: CucumberExecutionContext,
      val filters: Filters,
      plugins: () => Plugins
  ) {
    def startPlugins(): Unit = { plugins(); () }
  }

  object Setup {
    def apply(suiteClass: Class[_], cucumberOptions: CucumberOptions): Setup = {
      val classLoader: Supplier[ClassLoader] =
        () => suiteClass.getClassLoader
      val options = runtimeOptions(suiteClass, cucumberOptions)

      val uuidGenerator =
        new UuidGeneratorServiceLoader(classLoader, options).loadUuidGenerator()
      val bus: EventBus =
        new TimeServiceEventBus(
          Clock.systemUTC(),
          () => uuidGenerator.generateId()
        )
      val features = new FeaturePathFeatureSupplier(
        classLoader,
        options,
        new FeatureParser(() => bus.generateId())
      ).get().asScala.toList

      val exitStatus = new ExitStatus(options)
      val objectFactorySupplier = new ThreadLocalObjectFactorySupplier(
        new ObjectFactoryServiceLoader(classLoader, options)
      )
      val runnerSupplier = new ThreadLocalRunnerSupplier(
        options,
        bus,
        new BackendServiceLoader(classLoader, objectFactorySupplier),
        objectFactorySupplier
      )
      val context =
        new CucumberExecutionContext(bus, exitStatus, runnerSupplier)

      lazy val plugins = {
        val plugins = new Plugins(new PluginFactory(), options)
        plugins.addPlugin(exitStatus)
        plugins.setSerialEventBusOnEventListenerPlugins(bus)
        plugins
      }
      new Setup(
        options,
        bus,
        features,
        context,
        new Filters(options),
        () => plugins
      )
    }

    /** From lowest to highest precedence: `cucumber.properties`, the suite's
      * options, environment variables, system properties.
      */
    private def runtimeOptions(
        suiteClass: Class[_],
        o: CucumberOptions
    ): RuntimeOptions = {
      val packageName =
        Option(suiteClass.getPackage).map(_.getName).getOrElse("")
      val fromSuite = Map(
        Constants.FEATURES_PROPERTY_NAME -> o.features.mkString(","),
        Constants.GLUE_PROPERTY_NAME -> o.glue.mkString(","),
        Constants.PLUGIN_PROPERTY_NAME -> o.plugin.mkString(","),
        Constants.FILTER_TAGS_PROPERTY_NAME -> o.tags.getOrElse("")
      ).filter { case (_, value) => value.nonEmpty } ++ o.properties

      val merged =
        CucumberProperties.fromPropertiesFile().asScala ++
          fromSuite ++
          CucumberProperties.fromEnvironment().asScala ++
          CucumberProperties.fromSystemProperties().asScala

      val withDefaults =
        merged ++
          Seq(
            Constants.FEATURES_PROPERTY_NAME ->
              s"classpath:${packageName.replace('.', '/')}",
            Constants.GLUE_PROPERTY_NAME -> packageName
          ).filterNot { case (key, _) => merged.contains(key) }

      new CucumberPropertiesParser()
        .parse(withDefaults.asJava)
        .build()
    }
  }
}
