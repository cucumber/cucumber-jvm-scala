package io.cucumber.scalatest

import io.cucumber.core.options.RuntimeOptionsBuilder
import io.cucumber.core.runtime.{Runtime => CucumberRuntime}
import org.scalatest.{Args, Status, Suite}

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

  /** Runs the Cucumber scenarios.
    *
    * @param testName
    *   An optional name of one test to run. If None, all relevant tests should
    *   be run.
    * @param args
    *   the Args for this run
    * @return
    *   a Status object that indicates when all tests started by this method
    *   have completed, and whether or not a failure occurred.
    */
  abstract override def run(
      testName: Option[String],
      args: Args
  ): Status = {
    if (testName.isDefined) {
      throw new IllegalArgumentException(
        "Suite traits implemented by Cucumber do not support running a single test"
      )
    }

    val runtimeOptions = buildRuntimeOptions()
    val classLoader = getClass.getClassLoader

    val runtime = CucumberRuntime
      .builder()
      .withRuntimeOptions(runtimeOptions)
      .withClassLoader(new java.util.function.Supplier[ClassLoader] {
        override def get(): ClassLoader = classLoader
      })
      .build()

    runtime.run()

    val exitStatus = runtime.exitStatus()
    if (exitStatus == 0) {
      org.scalatest.SucceededStatus
    } else {
      throw new RuntimeException(
        s"Cucumber scenarios failed with exit status: $exitStatus"
      )
    }
  }

  private def buildRuntimeOptions(): io.cucumber.core.options.RuntimeOptions = {
    val packageName = getClass.getPackage.getName
    val builder = new RuntimeOptionsBuilder()

    // Add features
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

    builder.build()
  }
}
