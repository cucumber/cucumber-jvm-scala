package io.cucumber.scala

import io.cucumber.core.backend._
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

import scala.annotation.nowarn
import scala.util.Try

@nowarn
class ScalaDslStepsTest {

  @Test
  def testDefNoArg(): Unit = {

    var invoked = false

    class Glue extends ScalaDsl with EN {
      // On a single line to avoid difference between Scala versions for the location
      //@formatter:off
      Given("Something") { invoked = true }
      //@formatter:on
    }

    val glue = new Glue()

    assertClassStepDefinition(
      glue.registry.stepDefinitions.head,
      "Something",
      "ScalaDslStepsTest.scala:21",
      Array(),
      invoked
    )
  }

  @Test
  def testDefEmptyArg(): Unit = {

    var invoked = false

    class Glue extends ScalaDsl with EN {
      Given("Something") { () =>
        invoked = true
      }
    }

    val glue = new Glue()

    assertClassStepDefinition(
      glue.registry.stepDefinitions.head,
      "Something",
      "ScalaDslStepsTest.scala:42",
      Array(),
      invoked
    )
  }

  @Test
  def testDefWithArgs(): Unit = {

    var thenumber = 0
    var thecolour = ""

    class Glue extends ScalaDsl with EN {
      Given("""Oh boy, (\d+) (\s+) cukes""") { (num: Int, colour: String) =>
        thenumber = num
        thecolour = colour
      }
    }

    val glue = new Glue()

    assertClassStepDefinition(
      glue.registry.stepDefinitions.head,
      """Oh boy, (\d+) (\s+) cukes""",
      "ScalaDslStepsTest.scala:65",
      Array(java.lang.Integer.valueOf(5), "green"),
      thenumber == 5 && thecolour == "green"
    )
  }

  @Test
  def testDefThrowException(): Unit = {

    class GlueWithException extends ScalaDsl with EN {
      Given("Something") { () =>
        val x = 1 + 2 // A not useful line
        throw new PendingException()
      }
    }

    val glue = new GlueWithException()

    assertClassStepDefinitionThrow(
      glue.registry.stepDefinitions.head,
      classOf[PendingException],
      "io.cucumber.scala.ScalaDslStepsTest$GlueWithException",
      "ScalaDslStepsTest.scala",
      88,
      Array()
    )
  }

  // Note: this is a corner case that we should prevent to happen in the future
  @Test
  def testDefNullParameters(): Unit = {

    class Glue extends ScalaDsl with EN {
      Given("Something {}") { (str: String) =>
        // Nothing
        println(str)
      }
    }

    val glue = new Glue()

    assertClassStepDefinitionThrow(
      glue.registry.stepDefinitions.head,
      classOf[IncorrectStepDefinitionException],
      "io.cucumber.scala.ScalaDslStepsTest",
      "ScalaDslStepsTest.scala",
      323,
      Array(null)
    )
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectDefNoArg(): Unit = {

    var invoked = false

    object Glue extends ScalaDsl with EN {
      // On a single line to avoid difference between Scala versions for the location
      //@formatter:off
      Given("Something") { invoked = true }
      //@formatter:on
    }

    assertObjectStepDefinition(
      Glue.registry.stepDefinitions.head,
      "Something",
      "ScalaDslStepsTest.scala:138",
      Array(),
      invoked
    )
  }

  @Test
  def testObjectDefEmptyArg(): Unit = {

    var invoked = false

    object Glue extends ScalaDsl with EN {
      Given("Something") { () =>
        invoked = true
      }
    }

    assertObjectStepDefinition(
      Glue.registry.stepDefinitions.head,
      "Something",
      "ScalaDslStepsTest.scala:157",
      Array(),
      invoked
    )
  }

  @Test
  def testObjectDefWithArgs(): Unit = {

    var thenumber = 0
    var thecolour = ""

    object Glue extends ScalaDsl with EN {
      Given("""Oh boy, (\d+) (\s+) cukes""") { (num: Int, colour: String) =>
        thenumber = num
        thecolour = colour
      }
    }

    assertObjectStepDefinition(
      Glue.registry.stepDefinitions.head,
      """Oh boy, (\d+) (\s+) cukes""",
      "ScalaDslStepsTest.scala:178",
      Array(java.lang.Integer.valueOf(5), "green"),
      thenumber == 5 && thecolour == "green"
    )
  }

  @Test
  def testObjectDefThrowException(): Unit = {

    object GlueWithException extends ScalaDsl with EN {
      Given("Something") { () =>
        val x = 1 + 2 // A not useful line
        throw new PendingException()
      }
    }

    assertObjectStepDefinitionThrow(
      GlueWithException.registry.stepDefinitions.head,
      classOf[PendingException],
      "io.cucumber.scala.ScalaDslStepsTest$GlueWithException",
      "ScalaDslStepsTest.scala",
      199,
      Array()
    )
  }

  // Note: this is a corner case that we should prevent to happen in the future
  @Test
  def testObjectDefNullParameters(): Unit = {

    object Glue extends ScalaDsl with EN {
      Given("Something {}") { (str: String) =>
        // Nothing
        println(str)
      }
    }

    assertClassStepDefinitionThrow(
      Glue.registry.stepDefinitions.head,
      classOf[IncorrectStepDefinitionException],
      "io.cucumber.scala.ScalaDslStepsTest",
      "ScalaDslStepsTest.scala",
      323,
      Array(null)
    )
  }

  private def assertClassStepDefinition(
      stepDetails: ScalaStepDetails,
      pattern: String,
      location: String,
      args: Array[AnyRef],
      check: => Boolean
  ): Unit = {
    assertStepDefinition(
      ScalaStepDefinition(stepDetails, true),
      pattern,
      location,
      args,
      check
    )
  }

  private def assertObjectStepDefinition(
      stepDetails: ScalaStepDetails,
      pattern: String,
      location: String,
      args: Array[AnyRef],
      check: => Boolean
  ): Unit = {
    assertStepDefinition(
      ScalaStepDefinition(stepDetails, false),
      pattern,
      location,
      args,
      check
    )
  }

  private def assertStepDefinition(
      stepDefinition: StepDefinition,
      pattern: String,
      location: String,
      args: Array[AnyRef],
      check: => Boolean
  ): Unit = {
    assertEquals(pattern, stepDefinition.getPattern)
    assertEquals(location, stepDefinition.getLocation)
    stepDefinition.execute(args)
    assertTrue(check)
  }

  private def assertClassStepDefinitionThrow(
      stepDetails: ScalaStepDetails,
      underlyingExceptionClass: Class[_ <: Exception],
      exceptionClassName: String,
      exceptionFile: String,
      exceptionLine: Int,
      args: Array[AnyRef]
  ): Unit = {
    assertStepDefinitionThrow(
      ScalaStepDefinition(stepDetails, true),
      underlyingExceptionClass,
      exceptionClassName,
      exceptionFile,
      exceptionLine,
      args
    )
  }

  private def assertObjectStepDefinitionThrow(
      stepDetails: ScalaStepDetails,
      underlyingExceptionClass: Class[_ <: Exception],
      exceptionClassName: String,
      exceptionFile: String,
      exceptionLine: Int,
      args: Array[AnyRef]
  ): Unit = {
    assertStepDefinitionThrow(
      ScalaStepDefinition(stepDetails, false),
      underlyingExceptionClass,
      exceptionClassName,
      exceptionFile,
      exceptionLine,
      args
    )
  }

  private def assertStepDefinitionThrow(
      stepDefinition: StepDefinition,
      underlyingExceptionClass: Class[_ <: Exception],
      exceptionClassName: String,
      exceptionFile: String,
      exceptionLine: Int,
      args: Array[AnyRef]
  ): Unit = {
    val tried = Try(stepDefinition.execute(args))

    assertTrue(tried.isFailure)

    val ex = tried.failed.get
    assertTrue(ex.isInstanceOf[CucumberInvocationTargetException])

    val underlying = ex
      .asInstanceOf[CucumberInvocationTargetException]
      .getInvocationTargetExceptionCause
    assertEquals(underlyingExceptionClass, underlying.getClass)

    val matched = underlying.getStackTrace
      .filter(stepDefinition.isDefinedAt)
      .head

    // The result is different between Scala versions, that's why we don't check it precisely
    // assertEquals("$anonfun$can_provide_location_of_step$1", matched.getMethodName)
    assertTrue(
      matched.getClassName.contains(exceptionClassName),
      s"${matched.getClassName} did not contain $exceptionClassName"
    )
    assertEquals(exceptionFile, matched.getFileName)
    assertEquals(exceptionLine, matched.getLineNumber)
  }

}
