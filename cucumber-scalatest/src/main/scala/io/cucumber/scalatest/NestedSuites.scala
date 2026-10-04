package io.cucumber.scalatest

import org.scalatest.events.{
  IndentedText,
  MotionToSuppress,
  SuiteAborted,
  SuiteCompleted,
  SuiteStarting,
  TopOfClass
}
import org.scalatest.{Args, CompositeStatus, FailedStatus, Status, Suite}

import scala.util.Try

private[scalatest] object NestedSuites {

  /** Runs the nested suites of `parent` one after the other.
    *
    * ScalaTest's own `Suite.runNestedSuites` cannot be used: it runs nested
    * suites in a way that discards their `Status`, so a failure in a nested
    * suite would not make its parent fail. Cucumber also needs scenarios to run
    * sequentially, on the thread that runs the suite.
    */
  def runSequentially(parent: Suite, args: Args, level: Int): Status = {
    val statuses = parent.nestedSuites.map { suite =>
      val className = Some(suite.getClass.getName)
      val start = System.currentTimeMillis()
      args.reporter(
        SuiteStarting(
          args.tracker.nextOrdinal(),
          suite.suiteName,
          suite.suiteId,
          className,
          // Indented under its parent in the console: feature > scenario > step
          formatter = Some(
            IndentedText(
              "  " * level + suite.suiteName + ":",
              suite.suiteName,
              level
            )
          ),
          location = Some(TopOfClass(suite.getClass.getName))
        )
      )
      // Forked after the SuiteStarting ordinal: reporters sort events by
      // ordinal, so the nested ones must sit between SuiteStarting and
      // SuiteCompleted.
      val nestedArgs =
        args.copy(distributor = None, tracker = args.tracker.nextTracker())
      Try {
        val status = suite.run(None, nestedArgs)
        status.waitUntilCompleted()
        status
      }.map { status =>
        args.reporter(
          SuiteCompleted(
            args.tracker.nextOrdinal(),
            suite.suiteName,
            suite.suiteId,
            className,
            Some(System.currentTimeMillis() - start),
            formatter = Some(MotionToSuppress)
          )
        )
        status
      }.recover { case e: Exception =>
        args.reporter(
          SuiteAborted(
            args.tracker.nextOrdinal(),
            Option(e.getMessage).getOrElse(e.toString),
            suite.suiteName,
            suite.suiteId,
            className,
            Some(e),
            Some(System.currentTimeMillis() - start)
          )
        )
        FailedStatus
      }.get
    }
    new CompositeStatus(statuses.toSet)
  }
}
