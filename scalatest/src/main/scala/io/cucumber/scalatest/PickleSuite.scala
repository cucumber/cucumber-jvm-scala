package io.cucumber.scalatest

import io.cucumber.core.gherkin.Pickle
import io.cucumber.core.runner.Runner
import io.cucumber.core.runtime.CucumberExecutionContext
import org.scalatest._

import scala.util.Try

private[scalatest] final class PickleSuite(
    private val featureName: String,
    private val context: CucumberExecutionContext,
    private val pickle: Pickle,
    private val uniqueSuffix: Option[Int]
) extends Suite {

  private val testName =
    FilenameCompatibleNames.createName(pickle.getName, uniqueSuffix, false)

  override def suiteName: String = {
    val className = FilenameCompatibleNames.createName(featureName, false)
    s"$className $testName"
  }

  override def testNames: Set[String] = Set(testName)

  override def runTest(testName: String, args: Args): Status = {
    Try {
      context.runTestCase { (runner: Runner) =>
        runner.runPickle(pickle)
      }
      SucceededStatus
    }.getOrElse(FailedStatus)
  }

}

object PickleSuite {

  def withNoStepDescriptions(
      featureName: String,
      context: CucumberExecutionContext,
      pickle: Pickle,
      uniqueSuffix: Option[Int]
  ): PickleSuite = new PickleSuite(featureName, context, pickle, uniqueSuffix)

}
