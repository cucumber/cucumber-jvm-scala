package io.cucumber.scalatest

import io.cucumber.core.eventbus.EventBus
import io.cucumber.plugin.event.{Status, TestCaseFinished}
import org.scalatest.{Reporter, Tracker}

import scala.collection.mutable.ArrayBuffer

class ScalatestReporter(bus: EventBus, reporter: Reporter, tracker: Tracker) {

  private val stepErrors: ArrayBuffer[Throwable] = ArrayBuffer.empty

  init()

  println(reporter)
  println(tracker)

  private def init() = {
    bus.registerHandlerFor(classOf[TestCaseFinished], handleTestCaseResult)
  }

  private def handleTestCaseResult(event: TestCaseFinished): Unit = {
    event.getResult.getStatus match {
      case Status.PASSED | Status.UNUSED =>
      // do nothing
      case Status.SKIPPED =>
        if (stepErrors.isEmpty) {
          stepErrors.append(new SkippedThrowable(NotificationLevel.Scenario))
        }
//        stepErrors.headOption.foreach { throwable =>
//          reporter.apply(TestCanceled(tracker.nextOrdinal(), "skipped"))
//        }
      case Status.PENDING | Status.UNDEFINED =>
      // TODO
      case Status.AMBIGUOUS | Status.FAILED =>
      // TODO
    }
  }

}
