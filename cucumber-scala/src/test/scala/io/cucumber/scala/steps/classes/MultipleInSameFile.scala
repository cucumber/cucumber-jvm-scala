package io.cucumber.scala.steps.classes

import io.cucumber.scala.{EN, ScalaDsl}

class StepsA extends ScalaDsl with EN {

  Before {
    // Nothing
  }

  BeforeStep {
    // Nothing
  }

  After {
    // Nothing
  }

  AfterStep {
    // Nothing
  }

  Given("""stepA""") ((() => {
    // Nothing
  }))

}

class StepsB extends ScalaDsl with EN {

  When("""stepsB""") ((() => {
    // Nothing
  }))

}
