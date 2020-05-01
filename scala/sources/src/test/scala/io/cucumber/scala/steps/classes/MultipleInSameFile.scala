package io.cucumber.scala.steps.classes

import io.cucumber.scala.{EN, ScalaDsl}

class StepsA extends ScalaDsl with EN {

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

  Given("""stepA""") { () =>
    // Nothing
  }

}

class StepsB extends ScalaDsl with EN {

  When("""stepsB""") { () =>
    // Nothing
  }

}
