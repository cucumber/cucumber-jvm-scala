package tests.statichooks

import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.Assert.assertEquals

import scala.annotation.nowarn

@nowarn
object StaticHooksSteps extends ScalaDsl with EN {

  var countBeforeAll: Int = 0
  var countAfterAll: Int = 0

  BeforeAll {
    countBeforeAll = countBeforeAll + 1
  }

  AfterAll {
    countAfterAll = countAfterAll + 1
  }

  When("""I run scenario {string}""") { (scenarioName: String) =>
    println(s"Running scenario $scenarioName")
    ()
  }

  Then("""BeforeAll count is {int}""") { (count: Int) =>
    println(s"BeforeAll = $countBeforeAll")
    assertEquals(count, countBeforeAll)
  }

  Then("""AfterAll count is {int}""") { (count: Int) =>
    println(s"AfterAll = $countAfterAll")
    assertEquals(count, countAfterAll)
  }

}
