package cucumber.examples.scalacalculator

import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.scalatest.matchers.should.Matchers

class RpnCalculatorStepDefinitions extends ScalaDsl with EN with Matchers {

  val calc = new RpnCalculator

  When("""I add {double} and {double}""") { (arg1: Double, arg2: Double) =>
    calc push arg1
    calc push arg2
    calc push "+"
  }

  Then("the result is {double}") { (expected: Double) =>
    calc.value should be(expected +- 0.001)
  }

  Before("not @foo") { (scenario: Scenario) =>
    println(s"Runs before scenarios *not* tagged with @foo (${scenario.getId})")
  }
}
