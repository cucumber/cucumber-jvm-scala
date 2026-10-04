package io.cucumber.scalatest.fixtures.afterhook

import io.cucumber.scala.{EN, ScalaDsl, Scenario}

class AfterHookSteps extends ScalaDsl with EN {
  Given("a passing step") { () => }
  After { (_: Scenario) =>
    throw new IllegalStateException("after hook failed")
  }
}
