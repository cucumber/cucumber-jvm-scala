package io.cucumber.scalatest

import io.cucumber.core.options.{
  RuntimeOptionsBuilder,
  CucumberOptionsAnnotationParser
}
import io.cucumber.core.runtime.{Runtime => CucumberRuntime}
import org.scalatest.{Args, Status, Suite}

import scala.annotation.nowarn

/** A trait that allows Cucumber scenarios to be run with ScalaTest.
  *
  * Mix this trait into your test class and optionally annotate it with
  * `@CucumberOptions` to configure the Cucumber runtime.
  *
  * Example:
  * {{{
  * import io.cucumber.scalatest.CucumberSuite
  * import io.cucumber.core.options.CucumberOptions
  *
  * @CucumberOptions(
  *   features = Array("classpath:features"),
  *   glue = Array("com.example.stepdefinitions"),
  *   plugin = Array("pretty")
  * )
  * class RunCucumberTest extends CucumberSuite
  * }}}
  */
@nowarn
trait CucumberSuite extends Suite {

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
    // Try the built-in annotation parser which works with various annotations
    val annotationParser = new CucumberOptionsAnnotationParser()
    val packageName = getClass.getPackage.getName
    val featurePath = "classpath:" + packageName.replace('.', '/')

    try {
      val annotationOptions = annotationParser.parse(getClass).build()
      val options = new RuntimeOptionsBuilder().build(annotationOptions)

      // If no features were specified, use convention (classpath:package/name)
      if (options.getFeaturePaths().isEmpty) {
        val builder = new RuntimeOptionsBuilder()
        builder.addFeature(
          io.cucumber.core.feature.FeatureWithLines.parse(featurePath)
        )
        builder.addGlue(java.net.URI.create("classpath:" + packageName))
        builder.build(annotationOptions)
      } else {
        options
      }
    } catch {
      case _: Exception =>
        // If that fails, use convention based on package name
        val builder = new RuntimeOptionsBuilder()
        builder.addFeature(
          io.cucumber.core.feature.FeatureWithLines.parse(featurePath)
        )
        builder.addGlue(java.net.URI.create("classpath:" + packageName))
        builder.build()
    }
  }
}
