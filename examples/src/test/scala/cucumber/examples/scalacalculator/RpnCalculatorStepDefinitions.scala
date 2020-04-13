package cucumber.examples.scalacalculator

import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.junit.Assert._

class RpnCalculatorStepDefinitions extends ScalaDsl with EN {

  val calc = new RpnCalculator

  When("""I add {double} and {double}"""){ (arg1: Double, arg2: Double) =>
    calc push arg1
    calc push arg2
    calc push "+"
  }

  Then("the result is {double}") { expected: Double =>
    assertEquals(expected, calc.value, 0.001)
  }

  Before("not @foo"){ scenario : Scenario =>
    println("Runs before scenarios *not* tagged with @foo")
  }
}