package io.cucumber.scalatest

import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.runtime.CucumberExecutionContext
import io.cucumber.plugin.event.{
  EventHandler,
  HookTestStep,
  PickleStepTestStep,
  Result,
  TestCaseFinished,
  TestStepFinished,
  TestStepStarted,
  Status => CucumberStatus
}
import org.scalatest.events._
import org.scalatest.{
  Args,
  FailedStatus,
  Filter,
  Status,
  SucceededStatus,
  Suite
}

import scala.collection.mutable
import scala.util.Try
import scala.util.control.NonFatal

/** A scenario (or an example of a scenario outline), whose steps are reported
  * as ScalaTest tests while Cucumber executes the scenario.
  */
private[scalatest] final class ScenarioSuite(
    feature: Feature,
    pickle: Pickle,
    context: CucumberExecutionContext
) extends Suite {

  override def suiteName: String = {
    val name = pickle.getName
    if (name.nonEmpty) name
    else s"Scenario at line ${pickle.getLocation.getLine}"
  }

  // Scenario names are not unique, scenario lines are
  override def suiteId: String =
    s"${feature.getUri}:${pickle.getLocation.getLine}"

  // Steps are only known (and reported) once the scenario is executed
  override def testNames: Set[String] = Set.empty

  override def expectedTestCount(filter: Filter): Int = pickle.getSteps.size

  override protected def runTests(
      testName: Option[String],
      args: Args
  ): Status = {
    val reporter = args.reporter
    val className = Some(getClass.getName)
    val usedNames = mutable.Map.empty[String, Int]
    var currentStep: Option[String] = None
    var failed = false

    // The same step text can occur several times in a scenario
    def uniqueName(name: String): String = {
      val count = usedNames.getOrElse(name, 0) + 1
      usedNames(name) = count
      if (count == 1) name else s"$name ($count)"
    }

    def location(line: Int): Option[Location] =
      Some(LineInFile(line, feature.getUri.toString, None))

    // Steps are indented under their scenario in console reports
    def stepFormatter(name: String) =
      Some(IndentedText("    - " + name, name, 2))

    def starting(name: String, line: Int): Unit =
      reporter(
        TestStarting(
          args.tracker.nextOrdinal(),
          suiteName,
          suiteId,
          className,
          name,
          name,
          formatter = Some(MotionToSuppress),
          location = location(line)
        )
      )

    def finished(name: String, line: Int, result: Result): Unit = {
      val duration = Some(result.getDuration.toMillis)
      val error = Option(result.getError)
      def message(default: String): String =
        error.flatMap(e => Option(e.getMessage)).getOrElse(default)
      def failure(default: String): Unit = {
        failed = true
        reporter(
          TestFailed(
            args.tracker.nextOrdinal(),
            message(default),
            suiteName,
            suiteId,
            className,
            name,
            name,
            Vector.empty,
            Vector.empty,
            error,
            duration,
            formatter = stepFormatter(name),
            location = location(line)
          )
        )
      }
      result.getStatus match {
        case CucumberStatus.PASSED =>
          reporter(
            TestSucceeded(
              args.tracker.nextOrdinal(),
              suiteName,
              suiteId,
              className,
              name,
              name,
              Vector.empty,
              duration,
              formatter = stepFormatter(name),
              location = location(line)
            )
          )
        case CucumberStatus.SKIPPED =>
          reporter(
            TestCanceled(
              args.tracker.nextOrdinal(),
              message("Step skipped"),
              suiteName,
              suiteId,
              className,
              name,
              name,
              Vector.empty,
              error,
              duration,
              formatter = stepFormatter(name),
              location = location(line)
            )
          )
        case CucumberStatus.PENDING   => failure("Step is pending")
        case CucumberStatus.UNDEFINED => failure("Step is undefined")
        case CucumberStatus.AMBIGUOUS => failure("Step is ambiguous")
        case _                        => failure("Step failed")
      }
    }

    val onStepStarted: EventHandler[TestStepStarted] = event =>
      event.getTestStep match {
        case step: PickleStepTestStep =>
          val name = uniqueName(step.getStep.getKeyword + step.getStep.getText)
          currentStep = Some(name)
          starting(name, step.getStep.getLine)
        case _ => // Hooks are only reported when they do not pass
      }

    val onStepFinished: EventHandler[TestStepFinished] = event =>
      event.getTestStep match {
        case step: PickleStepTestStep =>
          currentStep.foreach(
            finished(_, step.getStep.getLine, event.getResult)
          )
        case hook: HookTestStep =>
          val status = event.getResult.getStatus
          if (
            status != CucumberStatus.PASSED && status != CucumberStatus.SKIPPED
          ) {
            val name = uniqueName(s"${hook.getHookType} hook")
            val line = pickle.getLocation.getLine
            starting(name, line)
            finished(name, line, event.getResult)
          }
        case _ =>
      }

    // Safety net: a scenario can never fail without a failed test
    val onCaseFinished: EventHandler[TestCaseFinished] = event =>
      if (!event.getResult.getStatus.isOk && !failed) {
        val name = uniqueName("Scenario")
        starting(name, pickle.getLocation.getLine)
        finished(name, pickle.getLocation.getLine, event.getResult)
      }

    Try {
      context.runTestCase { runner =>
        val bus = runner.getBus
        bus.registerHandlerFor(classOf[TestStepStarted], onStepStarted)
        bus.registerHandlerFor(classOf[TestStepFinished], onStepFinished)
        bus.registerHandlerFor(classOf[TestCaseFinished], onCaseFinished)
        val outcome = Try(runner.runPickle(pickle))
        bus.removeHandlerFor(classOf[TestStepStarted], onStepStarted)
        bus.removeHandlerFor(classOf[TestStepFinished], onStepFinished)
        bus.removeHandlerFor(classOf[TestCaseFinished], onCaseFinished)
        outcome.get
      }
    }.recover {
      // Cucumber could not even execute the scenario (e.g. broken glue)
      case NonFatal(e) =>
        val name = uniqueName("Scenario")
        val line = pickle.getLocation.getLine
        starting(name, line)
        finished(
          name,
          line,
          new Result(CucumberStatus.FAILED, java.time.Duration.ZERO, e)
        )
    }

    if (failed) FailedStatus else SucceededStatus
  }
}
