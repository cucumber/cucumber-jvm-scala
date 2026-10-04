package io.cucumber.scalatest.fixtures.beforeall

import io.cucumber.scala.{EN, ScalaDsl}

class BeforeAllSteps extends ScalaDsl with EN {
  Given("a passing step") { () => }
}

object BeforeAllSteps extends ScalaDsl with EN {
  var afterAllCalled = false

  BeforeAll {
    if (!afterAllCalled) throw new IllegalStateException("before all failed")
  }
  AfterAll { afterAllCalled = true }
}
