package io.cucumber.scalatest

import io.cucumber.core.exception.UnrecoverableExceptions.rethrowIfUnrecoverable
import io.cucumber.core.gherkin.Pickle
import io.cucumber.core.runtime.CucumberExecutionContext
import org.scalatest.events.TestFailed
import org.scalatest._

import scala.util.{Failure, Success, Try}

private class PickleSuite(
    pickle: Pickle,
    context: CucumberExecutionContext
) extends Suite {

  override def suiteName: String = pickle.getName

  override def testNames: Set[String] = Set(pickle.getName)

  override protected def runTest(testName: String, args: Args): Status = {
    // args.reporter.apply(TestStarting)

    var testFailed: Option[Throwable] = None

    val suite = this

    Try {
      context.runTestCase { runner =>

        val handler = new io.cucumber.plugin.event.EventHandler[io.cucumber.plugin.event.TestCaseFinished] {
          override def receive(event: io.cucumber.plugin.event.TestCaseFinished): Unit = {
            val result = event.getResult
            println(result)
            if (!result.getStatus.isOk) {
              val error = result.getError
              if (error != null) {
                testFailed = Some(error)
              } else {
                testFailed = Some(new RuntimeException(s"Test failed with status: ${result.getStatus}"))
              }
              args.reporter(
                TestFailed(
                  args.tracker.nextOrdinal(),
                  "boom",
                  suite.suiteName,
                  suite.suiteId,
                  Some(suite.getClass.getName),
                  testName,
                  testName,
                  IndexedSeq.empty,
                  IndexedSeq.empty,
                  testFailed
                )
              )
            }
          }
        }

        runner.getBus.registerHandlerFor(classOf[io.cucumber.plugin.event.TestCaseFinished], handler)

        runner.runPickle(pickle)

        runner.getBus.removeHandlerFor(classOf[io.cucumber.plugin.event.TestCaseFinished], handler)
      }
    } match {
      case Success(_) =>

        println("Pickle Suite " + testFailed)

        testFailed match {
          case Some(_) =>
            FailedStatus
          case None =>
            SucceededStatus
        }
      case Failure(exception) =>
        rethrowIfUnrecoverable(exception)
        // TODO see if we need/can use the exception in the failed status?
        FailedStatus
    }
  }
}
