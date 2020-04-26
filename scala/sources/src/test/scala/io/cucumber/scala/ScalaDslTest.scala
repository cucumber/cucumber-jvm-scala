package io.cucumber.scala

import java.util.concurrent.atomic.AtomicBoolean

import io.cucumber.core.backend.{CucumberInvocationTargetException, HookDefinition, StepDefinition, TestCaseState}
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}
import org.mockito.Mockito.mock

import scala.util.Try

class ScalaDslTest {

  private val fakeState = mock(classOf[TestCaseState])

  private val invoked = new AtomicBoolean()

  private def invoke(): Unit = {
    invoked.set(true)
  }

  @Before
  def beforeEach(): Unit = {
    // Reset the invoked flag
    invoked.set(false)
  }

  @Test
  def testBeforeHook(): Unit = {

    class Glue extends ScalaDsl {
      Before { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "", 1000)
  }

  @Test
  def testBeforeHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      Before("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "tagExpression", 1000)
  }

  @Test
  def testBeforeHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      Before(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "", 42)
  }

  @Test
  def testBeforeHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      Before("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "tagExpression", 42)
  }

  @Test
  def testBeforeStepHook(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "", 1000)
  }

  @Test
  def testBeforeStepHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testBeforeStepHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "", 42)
  }

  @Test
  def testBeforeStepHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "tagExpression", 42)
  }


  @Test
  def testAfterHook(): Unit = {

    class Glue extends ScalaDsl {
      After { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "", 1000)
  }

  @Test
  def testAfterHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      After("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "tagExpression", 1000)
  }

  @Test
  def testAfterHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      After(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "", 42)
  }

  @Test
  def testAfterHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      After("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "tagExpression", 42)
  }

  @Test
  def testAfterStepHook(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "", 1000)
  }

  @Test
  def testAfterStepHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testAfterStepHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "", 42)
  }

  @Test
  def testAfterStepHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "tagExpression", 42)
  }

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

    assertClassStepDefinition(glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:261", Array(), invoked)
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

    assertClassStepDefinition(glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:276", Array(), invoked)
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

    assertClassStepDefinition(glue.registry.stepDefinitions.head, """Oh boy, (\d+) (\s+) cukes""", "ScalaDslTest.scala:293", Array(new java.lang.Integer(5), "green"), thenumber == 5 && thecolour == "green")
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

    assertClassStepDefinitionThrow(glue.registry.stepDefinitions.head, "io.cucumber.scala.ScalaDslTest$GlueWithException", "ScalaDslTest.scala", 310, Array())
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectBeforeHook(): Unit = {

    object Glue extends ScalaDsl {
      Before { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "", 1000)
  }

  @Test
  def testObjectBeforeHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      Before("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectBeforeHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      Before(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "", 42)
  }

  @Test
  def testObjectBeforeHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      Before("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectBeforeStepHook(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "", 1000)
  }

  @Test
  def testObjectBeforeStepHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectBeforeStepHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "", 42)
  }

  @Test
  def testObjectBeforeStepHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "tagExpression", 42)
  }


  @Test
  def testObjectAfterHook(): Unit = {

    object Glue extends ScalaDsl {
      After { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "", 1000)
  }

  @Test
  def testObjectAfterHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      After("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectAfterHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      After(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "", 42)
  }

  @Test
  def testObjectAfterHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      After("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectAfterStepHook(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "", 1000)
  }

  @Test
  def testObjectAfterStepHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectAfterStepHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "", 42)
  }

  @Test
  def testObjectAfterStepHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectDefNoArg(): Unit = {

    var invoked = false

    object Glue extends ScalaDsl with EN {
      // On a single line to avoid difference between Scala versions for the location
      //@formatter:off
      Given("Something") { invoked = true }
      //@formatter:on
    }

    assertObjectStepDefinition(Glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:523", Array(), invoked)
  }

  @Test
  def testObjectDefEmptyArg(): Unit = {

    var invoked = false

    object Glue extends ScalaDsl with EN {
      Given("Something") { () =>
        invoked = true
      }
    }

    assertObjectStepDefinition(Glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:536", Array(), invoked)
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

    assertObjectStepDefinition(Glue.registry.stepDefinitions.head, """Oh boy, (\d+) (\s+) cukes""", "ScalaDslTest.scala:551", Array(new java.lang.Integer(5), "green"), thenumber == 5 && thecolour == "green")
  }

  @Test
  def testObjectDefThrowException(): Unit = {

    object GlueWithException extends ScalaDsl with EN {
      Given("Something") { () =>
        val x = 1 + 2 // A not useful line
        throw new PendingException()
      }
    }

    assertObjectStepDefinitionThrow(GlueWithException.registry.stepDefinitions.head, "io.cucumber.scala.ScalaDslTest$GlueWithException", "ScalaDslTest.scala", 566, Array())
  }

  private def assertClassHook(hookDetails: ScalaHookDetails, tagExpression: String, order: Int): Unit = {
    assertHook(ScalaHookDefinition(hookDetails, true), tagExpression, order)
  }

  private def assertObjectHook(hookDetails: ScalaHookDetails, tagExpression: String, order: Int): Unit = {
    assertHook(ScalaHookDefinition(hookDetails, false), tagExpression, order)
  }

  private def assertHook(hook: HookDefinition, tagExpression: String, order: Int): Unit = {
    assertEquals(tagExpression, hook.getTagExpression)
    assertEquals(order, hook.getOrder)
    hook.execute(fakeState)
    assertTrue(invoked.get())
  }

  private def assertClassStepDefinition(stepDetails: ScalaStepDetails, pattern: String, location: String, args: Array[AnyRef], check: => Boolean): Unit = {
    assertStepDefinition(ScalaStepDefinition(stepDetails, true), pattern, location, args, check)
  }

  private def assertObjectStepDefinition(stepDetails: ScalaStepDetails, pattern: String, location: String, args: Array[AnyRef], check: => Boolean): Unit = {
    assertStepDefinition(ScalaStepDefinition(stepDetails, false), pattern, location, args, check)
  }

  private def assertStepDefinition(stepDefinition: StepDefinition, pattern: String, location: String, args: Array[AnyRef], check: => Boolean): Unit = {
    assertEquals(pattern, stepDefinition.getPattern)
    assertEquals(location, stepDefinition.getLocation)
    stepDefinition.execute(args)
    assertTrue(check)
  }

  private def assertClassStepDefinitionThrow(stepDetails: ScalaStepDetails, exceptionClassName: String, exceptionFile: String, exceptionLine: Int, args: Array[AnyRef]): Unit = {
    assertStepDefinitionThrow(ScalaStepDefinition(stepDetails, true), exceptionClassName, exceptionFile, exceptionLine, args)
  }

  private def assertObjectStepDefinitionThrow(stepDetails: ScalaStepDetails, exceptionClassName: String, exceptionFile: String, exceptionLine: Int, args: Array[AnyRef]): Unit = {
    assertStepDefinitionThrow(ScalaStepDefinition(stepDetails, false), exceptionClassName, exceptionFile, exceptionLine, args)
  }

  private def assertStepDefinitionThrow(stepDefinition: StepDefinition, exceptionClassName: String, exceptionFile: String, exceptionLine: Int, args: Array[AnyRef]): Unit = {
    val tried = Try(stepDefinition.execute(args))

    assertTrue(tried.isFailure)

    val ex = tried.failed.get
    assertTrue(ex.isInstanceOf[CucumberInvocationTargetException])

    val matched = ex.asInstanceOf[CucumberInvocationTargetException]
      .getInvocationTargetExceptionCause
      .getStackTrace
      .filter(stepDefinition.isDefinedAt)
      .head

    // The result is different between Scala versions, that's why we don't check it precisely
    //assertEquals("$anonfun$can_provide_location_of_step$1", matched.getMethodName)
    assertTrue(matched.getClassName.contains(exceptionClassName))
    assertEquals(exceptionFile, matched.getFileName)
    assertEquals(exceptionLine, matched.getLineNumber)
  }

}

