package io.cucumber.scalatest.fixtures.passing

import io.cucumber.scala.{EN, ScalaDsl}

class PassingSteps extends ScalaDsl with EN {
  Given("a passing step") { () => }
  When("another passing step") { () => }
  Then("a final step") { () => }
  Then("the value is {int}") { (_: Int) => }
}
