package io.cucumber.scala.steps.traits

import io.cucumber.scala.{EN, ScalaDsl}

trait TraitWithSteps extends ScalaDsl with EN {

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

  Given("""Given step""") ((() => {
    // Nothing
  }))

}

class StepsInTrait extends TraitWithSteps {}
