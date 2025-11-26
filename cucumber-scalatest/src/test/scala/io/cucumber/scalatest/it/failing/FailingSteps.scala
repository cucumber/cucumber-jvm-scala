package io.cucumber.scalatest.it.failing

import io.cucumber.scala.{EN, ScalaDsl}

class FailingSteps extends ScalaDsl with EN {

  When("""a step that fails""") { () =>
    assert(false)
  }

}
