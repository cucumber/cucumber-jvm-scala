package io.cucumber.scalatest

import io.cucumber.scalatest.it.failing.FailingSuite
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.{Args, DoNotDiscover}
import org.scalatest.events.Event

import scala.collection.mutable

class CucumberSuiteTest extends AnyFunSuite with Matchers {

  test("successful scenario execution should succeed") {
    // Create a test suite with a feature that will pass
    val suite = new TestSuiteWithPassingScenario()

    val events = mutable.ListBuffer[Event]()
    val args = Args(reporter = (e: Event) => events += e)

    // Run should succeed
    val status = suite.run(None, args)
    status.succeeds() shouldBe true
  }

  test("failing scenario should fail") {
    val suite = new FailingSuite

    val events = mutable.ListBuffer[Event]()
    val args = Args(reporter = (e: Event) => events += e)

    val status = suite.run(None, args)

    status.succeeds() shouldEqual false
  }

  test("run with testName should throw IllegalArgumentException") {
    val suite = new TestSuiteWithPassingScenario()

    val args = Args(reporter = (_: Event) => ())

    // Running with a specific test name should throw IllegalArgumentException
    val exception = intercept[IllegalArgumentException] {
      suite.run(Some("testName"), args)
    }
    exception.getMessage should include("Running a single test by name is not supported in CucumberSuite")
  }

}

@DoNotDiscover
class TestSuiteWithPassingScenario extends CucumberSuite {
  override val cucumberOptions: CucumberOptions = CucumberOptions(
    features = List.empty,
    glue = List("io.cucumber.scalatest.nonexistent"),
    plugin = List.empty
  )
}
