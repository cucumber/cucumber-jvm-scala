package io.cucumber.scala.steps.dependencyinjection

import io.cucumber.scala.{EN, ScalaDsl}

class Injected extends ScalaDsl with EN {

  var x: String = _

  Given("""injected steps""") { () =>
    // Nothing
  }

}
