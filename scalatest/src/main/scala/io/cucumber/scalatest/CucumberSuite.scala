package io.cucumber.scalatest

import io.cucumber.core.eventbus.EventBus
import io.cucumber.core.feature.FeatureParser
import io.cucumber.core.filter.Filters
import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.options.{
  CucumberProperties,
  CucumberPropertiesParser,
  CucumberSuiteOptionsParser,
  RuntimeOptions
}
import io.cucumber.core.plugin.{PluginFactory, Plugins}
import io.cucumber.core.resource.ClassLoaders
import io.cucumber.core.runtime.SynchronizedEventBus.synchronize
import io.cucumber.core.runtime._
import org.scalatest._

import java.time.Clock
import java.util.function.{Predicate, Supplier}
import java.util.{Optional, UUID}
import scala.jdk.CollectionConverters._
import scala.util.Try

trait CucumberSuite extends Suite { this: CucumberSuiteOptions =>

  private lazy val parsed = CucumberSuite.parse(this)

  override def nestedSuites: IndexedSeq[Suite] = parsed.children.toIndexedSeq

  override protected def runNestedSuites(args: Args): Status = {
    println("runNestedSuites")
    lazy val runChildren = {
      println("runChildren")
      super.runNestedSuites(args)
    }
    Try {
      println("try")
      parsed.context.runFeatures(() => {
        println("runFeatures")
        runChildren
        ()
      })
      runChildren
    }
      .recover { case e: Throwable =>
        println("recover")
        e.printStackTrace()
        FailedStatus
      }
      .getOrElse(FailedStatus)
  }
}

object CucumberSuite {

  private def parse[T <: CucumberSuiteOptions](
      options: T
  ): CucumberSuiteParsed = {

    println(s"parse $options")

    val propertiesFileOptions: RuntimeOptions =
      new CucumberPropertiesParser()
        .parse(CucumberProperties.fromPropertiesFile)
        .build

    println("tata")

    val annotationOptions: RuntimeOptions =
      CucumberSuiteOptionsParser
        .unsafeParse(options)
        .build(propertiesFileOptions)

    println("titi")

    val environmentOptions: RuntimeOptions =
      new CucumberPropertiesParser()
        .parse(CucumberProperties.fromEnvironment)
        .build(annotationOptions)

    val runtimeOptions: RuntimeOptions =
      new CucumberPropertiesParser()
        .parse(CucumberProperties.fromSystemProperties)
        .enablePublishPlugin
        .build(environmentOptions)

    val bus: EventBus = synchronize(
      new TimeServiceEventBus(Clock.systemUTC, () => UUID.randomUUID())
    )

    println("toto")

    // Parse the features early. Don't proceed when there are lexer errors
    val parser: FeatureParser = new FeatureParser(() => bus.generateId())
    val classLoader: Supplier[ClassLoader] = () =>
      ClassLoaders.getDefaultClassLoader
    val featureSupplier: FeaturePathFeatureSupplier =
      new FeaturePathFeatureSupplier(classLoader, runtimeOptions, parser)
    val features: Seq[Feature] = featureSupplier.get.asScala.toSeq

    // Create plugins after feature parsing to avoid the creation of empty
    // files on lexer errors.
    val plugins = new Plugins(new PluginFactory, runtimeOptions)
    val exitStatus = new ExitStatus(runtimeOptions)
    plugins.addPlugin(exitStatus)

    val objectFactoryServiceLoader =
      new ObjectFactoryServiceLoader(classLoader, runtimeOptions)
    val objectFactorySupplier = new ThreadLocalObjectFactorySupplier(
      objectFactoryServiceLoader
    )
    val backendSupplier = new BackendServiceLoader(
      () => options.getClass.getClassLoader,
      objectFactorySupplier
    )
    val runnerSupplier = new ThreadLocalRunnerSupplier(
      runtimeOptions,
      bus,
      backendSupplier,
      objectFactorySupplier
    )
    val context = new CucumberExecutionContext(bus, exitStatus, runnerSupplier)

    val filters: Predicate[Pickle] = new Filters(runtimeOptions)

    val groupedByName: Map[Optional[String], Seq[Feature]] =
      features.groupBy(_.getName)
    val children = features
      .map { feature =>
        val uniqueSuffix: Option[Int] = FilenameCompatibleNames
          .uniqueSuffix(groupedByName, feature, (f: Feature) => f.getName)
        FeatureSuite.createUnsafe(feature, uniqueSuffix, filters, context)
      }
      .filterNot(_.isEmpty)

    println(children)

    CucumberSuiteParsed(context, children)
  }

  private case class CucumberSuiteParsed(
      context: CucumberExecutionContext,
      children: Seq[FeatureSuite]
  )

}
