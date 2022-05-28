package io.cucumber.scala.steps.dependencyinjection

import io.cucumber.scala.{EN, ScalaDsl}

class Injector(injected: Injected) extends ScalaDsl with EN {

  Then("""Injector""") { () =>
    println(injected.x)
  }

}
