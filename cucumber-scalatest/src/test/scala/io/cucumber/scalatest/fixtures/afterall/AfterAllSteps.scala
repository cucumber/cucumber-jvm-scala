package io.cucumber.scalatest.fixtures.afterall

import io.cucumber.scala.{EN, ScalaDsl}

class AfterAllSteps extends ScalaDsl with EN {
  Given("a passing step") { () => }
}

object AfterAllSteps extends ScalaDsl with EN {
  // A bare `throw` in the hook body makes the overload ambiguous
  private def fail(): Unit = throw new IllegalStateException("after all failed")

  AfterAll { fail() }
}
