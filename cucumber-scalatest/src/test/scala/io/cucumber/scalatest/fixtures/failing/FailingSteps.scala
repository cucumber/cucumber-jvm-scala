package io.cucumber.scalatest.fixtures.failing

import io.cucumber.scala.{EN, ScalaDsl}

class FailingSteps extends ScalaDsl with EN {
  Given("a passing step") { () => }
  When("a failing step") { () => throw new AssertionError("boom") }
  Then("a skipped step") { () => }
}
