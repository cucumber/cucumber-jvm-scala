package `object`

import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.jupiter.api.Assertions.assertEquals

import scala.annotation.nowarn

@nowarn
object ObjectSteps extends ScalaDsl with EN {

  private var calculator: Calculator = _
  private var result: Int = -1

  Given("""I have a calculator""") {
    calculator = new Calculator()
  }

  When("""I do {int} + {int}""") { (a: Int, b: Int) =>
    result = calculator.add(a, b)
  }

  Then("""I got {int}""") { (expectedResult: Int) =>
    assertEquals(expectedResult, result)
  }

  private class Calculator {
    def add(a: Int, b: Int) = a + b
  }

}
