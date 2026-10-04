package io.cucumber.scalatest.fixtures.beforehook

import io.cucumber.scala.{EN, ScalaDsl, Scenario}

class BeforeHookSteps extends ScalaDsl with EN {
  Before { (_: Scenario) =>
    throw new IllegalStateException("before hook failed")
  }
  Given("a passing step") { () => }
  Then("another passing step") { () => }
}
