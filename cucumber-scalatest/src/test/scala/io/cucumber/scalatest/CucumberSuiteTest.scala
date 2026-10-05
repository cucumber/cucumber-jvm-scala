package io.cucumber.scalatest

import io.cucumber.scalatest.fixtures.failing.{
  EmptyCucumberSuite,
  FailingCucumberSuite,
  FilteredCucumberSuite
}
import io.cucumber.scalatest.fixtures.afterall.AfterAllCucumberSuite
import io.cucumber.scalatest.fixtures.beforehook.BeforeHookCucumberSuite
import io.cucumber.scalatest.fixtures.beforeall.{
  BeforeAllCucumberSuite,
  BeforeAllSteps
}
import io.cucumber.scalatest.fixtures.afterhook.AfterHookCucumberSuite
import io.cucumber.scalatest.fixtures.passing.PassingCucumberSuite
import org.scalatest.events._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.{Args, Reporter, Status, Suite}

import scala.collection.mutable.ListBuffer

class CucumberSuiteTest extends AnyFunSuite with Matchers {

  private class Recorder extends Reporter {
    val events = ListBuffer.empty[Event]
    override def apply(event: Event): Unit = events += event

    /** Compact view of the hierarchy */
    def summary: List[String] = events.toList.collect {
      case e: SuiteStarting  => s"suite ${e.suiteName}"
      case e: SuiteCompleted => s"end   ${e.suiteName}"
      case e: SuiteAborted   => s"abort ${e.suiteName}"
      case e: TestSucceeded  => s"  passed   ${e.testText}"
      case e: TestFailed     => s"  failed   ${e.testText}"
      case e: TestCanceled   => s"  canceled ${e.testText}"
    }
    def failures: List[TestFailed] = events.toList.collect {
      case e: TestFailed => e
    }
  }

  private def run(suite: Suite): (Status, Recorder) = {
    val recorder = new Recorder
    val status = suite.run(None, Args(reporter = recorder))
    status.waitUntilCompleted()
    (status, recorder)
  }

  test("passing features succeed and report features, scenarios and steps") {
    val (status, recorder) = run(new PassingCucumberSuite)

    status.succeeds() shouldBe true
    recorder.failures shouldBe empty
    recorder.summary shouldBe List(
      "suite Passing feature",
      "suite Simple scenario",
      "  passed   Given a passing step",
      "  passed   When another passing step",
      "  passed   Then a final step",
      "end   Simple scenario",
      "suite Outline with 1",
      "  passed   Given a passing step",
      "  passed   Then the value is 1",
      "end   Outline with 1",
      "suite Outline with 2",
      "  passed   Given a passing step",
      "  passed   Then the value is 2",
      "end   Outline with 2",
      "end   Passing feature"
    )
  }

  test("a failing step fails its scenario, its feature and the suite") {
    val (status, recorder) = run(new FailingCucumberSuite)

    status.succeeds() shouldBe false
    recorder.summary shouldBe List(
      "suite Failing feature",
      "suite Passing scenario",
      "  passed   Given a passing step",
      "end   Passing scenario",
      "suite Failing step",
      "  passed   Given a passing step",
      "  failed   When a failing step",
      "  canceled Then a skipped step",
      "end   Failing step",
      "suite Undefined step",
      "  passed   Given a passing step",
      "  failed   When a step nobody defined",
      "end   Undefined step",
      "end   Failing feature",
      "suite Other feature",
      "suite Other passing scenario",
      "  passed   Given a passing step",
      "end   Other passing scenario",
      "end   Other feature"
    )

    val List(failed, undefined) = recorder.failures
    failed.message shouldBe "boom"
    failed.throwable.map(_.getClass) shouldBe Some(classOf[AssertionError])
    failed.suiteName shouldBe "Failing step"
    undefined.message should include("undefined")
  }

  test("suites are nested with unique ids") {
    val features = new FailingCucumberSuite().nestedSuites
    features.map(_.suiteName) shouldBe Seq("Failing feature", "Other feature")
    features.head.nestedSuites.map(_.suiteName) shouldBe
      Seq("Passing scenario", "Failing step", "Undefined step")

    val ids = features.flatMap(f => f.suiteId +: f.nestedSuites.map(_.suiteId))
    ids.distinct should have size ids.size.toLong
  }

  test("the tags option selects the scenarios to run") {
    val (status, recorder) = run(new FilteredCucumberSuite)

    status.succeeds() shouldBe true
    recorder.failures shouldBe empty
    recorder.summary.filter(_.startsWith("suite")) shouldBe List(
      "suite Failing feature",
      "suite Passing scenario"
    )
  }

  test("features without any selected scenario are not reported") {
    val (status, recorder) = run(new EmptyCucumberSuite)

    status.succeeds() shouldBe true
    recorder.events shouldBe empty
  }

  test("a failing After hook fails the scenario") {
    val (status, recorder) = run(new AfterHookCucumberSuite)

    status.succeeds() shouldBe false
    recorder.summary shouldBe List(
      "suite After hook feature",
      "suite Failing After hook",
      "  passed   Given a passing step",
      "  failed   AFTER hook",
      "end   Failing After hook",
      "end   After hook feature"
    )
    recorder.failures.map(_.message) shouldBe List("after hook failed")
  }

  test("a failing Before hook fails the scenario and skips its steps") {
    val (status, recorder) = run(new BeforeHookCucumberSuite)

    status.succeeds() shouldBe false
    recorder.summary shouldBe List(
      "suite Before hook feature",
      "suite Failing Before hook",
      "  failed   BEFORE hook",
      "  canceled Given a passing step",
      "  canceled Then another passing step",
      "end   Failing Before hook",
      "end   Before hook feature"
    )
    recorder.failures.map(_.message) shouldBe List("before hook failed")
  }

  test("a failing BeforeAll hook aborts the run, AfterAll hooks still run") {
    val recorder = new Recorder
    val e = intercept[IllegalStateException] {
      new BeforeAllCucumberSuite().run(None, Args(reporter = recorder))
    }

    e.getMessage shouldBe "before all failed"
    BeforeAllSteps.afterAllCalled shouldBe true
    recorder.events shouldBe empty
  }

  test("a failing AfterAll hook does not fail scenarios but aborts the suite") {
    val recorder = new Recorder
    val e = intercept[IllegalStateException] {
      new AfterAllCucumberSuite().run(None, Args(reporter = recorder))
    }

    e.getMessage shouldBe "after all failed"
    // Scenarios ran and passed: only the suite itself is reported as broken
    recorder.failures shouldBe empty
    recorder.summary shouldBe List(
      "suite AfterAll feature",
      "suite Passing before the AfterAll fails",
      "  passed   Given a passing step",
      "end   Passing before the AfterAll fails",
      "end   AfterAll feature"
    )
  }

  test("test names include the feature and scenario, test texts do not") {
    val (_, recorder) = run(new PassingCucumberSuite)
    val succeeded = recorder.events.toList.collect { case e: TestSucceeded =>
      e
    }

    succeeded.head.testName shouldBe
      "Passing feature > Simple scenario > Given a passing step"
    succeeded.head.testText shouldBe "Given a passing step"
    succeeded.map(_.testName).distinct should have size succeeded.size.toLong
  }

  test("running a single test by name is not supported") {
    intercept[IllegalArgumentException] {
      new PassingCucumberSuite().run(Some("foo"), Args(reporter = new Recorder))
    }
  }
}
