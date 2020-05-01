package io.cucumber.scala.steps.traits

import io.cucumber.scala.{EN, ScalaDsl}

trait TraitWithSteps extends ScalaDsl with EN {

  Before { _ =>
    // Nothing
  }

  BeforeStep { _ =>
    // Nothing
  }

  After { _ =>
    // Nothing
  }

  AfterStep { _ =>
    // Nothing
  }

  Given("""Given step""") { () =>
    // Nothing
  }

}

class StepsInTrait extends TraitWithSteps {

}
