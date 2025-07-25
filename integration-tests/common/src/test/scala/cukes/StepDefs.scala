package cukes

import cukes.model.{Cukes, Person, Snake}
import java.util.{List => JList, Map => JMap}

import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.jupiter.api.Assertions.assertEquals

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

/** Test step definitions to exercise Scala cucumber
  */
@nowarn
class CukesStepDefinitions extends ScalaDsl with EN {

  var calorieCount = 0.0
  var intBelly: Int = 0
  var longBelly: Long = 0L
  var stringBelly: String = ""
  var doubleBelly: Double = 0.0
  var floatBelly: Float = 0.0f
  var shortBelly: Short = 0.toShort
  var byteBelly: Byte = 0.toByte
  var bigDecimalBelly: BigDecimal = BigDecimal(0)
  var bigIntBelly: BigInt = BigInt(0)
  var charBelly: Char = 'A'
  var boolBelly: Boolean = false
  var snake: Snake = null
  var person: Person = null
  var cukes: JList[Cukes] = null
  var gin: Int = 13
  var vermouth: Int = 42
  var maritinis: Int = 0

  Given("""I have {} {string} in my belly""") { (howMany: Int, what: String) =>
  }

  Given("""^I have the following foods :$""") { (table: DataTable) =>
    val maps: JList[JMap[String, String]] =
      table.asMaps(classOf[String], classOf[String])
    calorieCount =
      maps.asScala.map(_.get("CALORIES")).map(_.toDouble).fold(0.0)(_ + _)
  }
  And("""have eaten {double} calories today""") { (calories: Double) =>
    assertEquals(calories, calorieCount, 0.0)
  }

  Given("""I have eaten an int {int}""") { (arg0: Int) =>
    intBelly = arg0
  }
  Then("""^I should have one hundred in my belly$""") { () =>
    assertEquals(100, intBelly)
  }

  Given("""I have eaten a long {long}""") { (arg0: Long) =>
    longBelly = arg0
  }
  Then("""^I should have long one hundred in my belly$""") { () =>
    assertEquals(100L, longBelly)
  }

  Given("""^I have eaten "(.*)"$""") { (arg0: String) =>
    stringBelly = arg0
  }
  Then("""^I should have numnumnum in my belly$""") { () =>
    assertEquals("numnumnum", stringBelly)
  }

  Given("""I have eaten {double} doubles""") { (arg0: Double) =>
    doubleBelly = arg0
  }
  Then("""^I should have one and a half doubles in my belly$""") { () =>
    assertEquals(1.5, doubleBelly, 0.0)
  }

  Given("""I have eaten {} floats""") { (arg0: Float) =>
    floatBelly = arg0
  }
  Then("""^I should have one and a half floats in my belly$""") { () =>
    assertEquals(1.5f, floatBelly, 0.0)
  }

  Given("""I have eaten a short {short}""") { (arg0: Short) =>
    shortBelly = arg0
  }
  Then("""^I should have short one hundred in my belly$""") { () =>
    assertEquals(100.toShort, shortBelly)
  }

  Given("""I have eaten a byte {byte}""") { (arg0: Byte) =>
    byteBelly = arg0
  }
  Then("""^I should have two byte in my belly$""") { () =>
    assertEquals(2.toByte, byteBelly)
  }

  Given("""I have eaten {bigdecimal} big decimals""") {
    (arg0: java.math.BigDecimal) =>
      bigDecimalBelly = arg0
  }
  Then("""^I should have one and a half big decimals in my belly$""") { () =>
    assertEquals(BigDecimal(1.5), bigDecimalBelly)
  }

  Given("""I have eaten {biginteger} big int""") {
    (arg0: java.math.BigInteger) =>
      bigIntBelly = arg0.intValue()
  }
  Then("""^I should have a ten big int in my belly$""") { () =>
    assertEquals(BigInt(10), bigIntBelly)
  }

  Given("""I have eaten char '{char}'""") { (arg0: Char) =>
    charBelly = 'C'
  }
  Then("""^I should have character C in my belly$""") { () =>
    assertEquals('C', charBelly)
  }

  Given("""I have eaten boolean {boolean}""") { (arg0: Boolean) =>
    boolBelly = arg0
  }
  Then("""^I should have truth in my belly$""") { () =>
    assertEquals(true, boolBelly)
  }

  Given("""I have a table the sum of all rows should be {int} :""") {
    (value: Int, table: DataTable) =>
      assertEquals(
        value,
        table
          .asList(classOf[String])
          .asScala
          .drop(1)
          .map(String.valueOf(_: String).toInt)
          .foldLeft(0)(_ + _)
      )
  }

  Given("""I see in the distance ... {snake}""") { (s: Snake) =>
    snake = s
  }
  Then("""^I have a snake of length (\d+) moving (.*)$""") {
    (size: Int, dir: String) =>
      assertEquals(size, snake.length)
      assertEquals(Symbol(dir), snake.direction)
  }

  Given("""I have a person {person}""") { (p: Person) =>
    person = p
  }

  Then("""^he should say \"(.*)\"""") { (s: String) =>
    assertEquals(person.hello, s)
  }

  Given("^I have eaten the following cukes$") { (cs: JList[Cukes]) =>
    cukes = cs
  }

  Then("""I should have eaten {int} cukes""") { (total: Int) =>
    assertEquals(total, cukes.asScala.map(_.number).sum)
  }

  And("^they should have been (.*)$") { (colors: String) =>
    assertEquals(colors, cukes.asScala.map(_.color).mkString(", "))
  }

  Given("^I drink gin and vermouth$") { () =>
    gin = 13
    vermouth = 42
  }

  When("^I shake my belly$") { // note the lack of  () =>
    maritinis += vermouth * gin
  }

  Then("^I should have lots of martinis$") { () =>
    assertEquals(13 * 42, maritinis)
  }
}

@nowarn
class ThenDefs extends ScalaDsl with EN {
  Then("""^I am "([^"]*)"$""") { (arg0: String) => }
}
