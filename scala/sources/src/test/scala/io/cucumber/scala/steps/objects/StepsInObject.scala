package io.cucumber.scala.steps.objects

import io.cucumber.scala.{EN, ScalaDsl}

object StepsInObject extends ScalaDsl with EN {

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
