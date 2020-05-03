package io.cucumber.scala

import java.util.concurrent.atomic.AtomicBoolean

import io.cucumber.core.backend._
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}
import org.mockito.Mockito.mock

class ScalaDslHooksTest {

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
      Before { _: Scenario =>
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
  def testBeforeHookNoArg(): Unit = {

    class Glue extends ScalaDsl {
      Before {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "", 1000)
  }

  @Test
  def testBeforeHookWithTagNoArg(): Unit = {

    class Glue extends ScalaDsl {
      Before("tagExpression") {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "tagExpression", 1000)
  }

  @Test
  def testBeforeHookWithOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      Before(42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "", 42)
  }

  @Test
  def testBeforeHookWithTagAndOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      Before("tagExpression", 42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "tagExpression", 42)
  }

  @Test
  def testBeforeStepHook(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep { _: Scenario =>
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
  def testBeforeStepHookNoArg(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "", 1000)
  }

  @Test
  def testBeforeStepHookWithTagNoArg(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep("tagExpression") {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testBeforeStepHookWithOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep(42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "", 42)
  }

  @Test
  def testBeforeStepHookWithTagAndOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep("tagExpression", 42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "tagExpression", 42)
  }

  @Test
  def testAfterHook(): Unit = {

    class Glue extends ScalaDsl {
      After { _: Scenario =>
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
  def testAfterHookNoArg(): Unit = {

    class Glue extends ScalaDsl {
      After {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "", 1000)
  }

  @Test
  def testAfterHookWithTagNoArg(): Unit = {

    class Glue extends ScalaDsl {
      After("tagExpression") {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "tagExpression", 1000)
  }

  @Test
  def testAfterHookWithOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      After(42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "", 42)
  }

  @Test
  def testAfterHookWithTagAndOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      After("tagExpression", 42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "tagExpression", 42)
  }

  @Test
  def testAfterStepHook(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep { _: Scenario =>
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
  def testAfterStepHookNoArg(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "", 1000)
  }

  @Test
  def testAfterStepHookWithTagNoArg(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep("tagExpression") {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testAfterStepHookWithOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep(42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "", 42)
  }

  @Test
  def testAfterStepHookWithTagAndOrderNoArg(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep("tagExpression", 42) {
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "tagExpression", 42)
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectBeforeHook(): Unit = {

    object Glue extends ScalaDsl {
      Before { _: Scenario =>
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
  def testObjectBeforeHookNoArg(): Unit = {

    object Glue extends ScalaDsl {
      Before {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "", 1000)
  }

  @Test
  def testObjectBeforeHookWithTagNoArg(): Unit = {

    object Glue extends ScalaDsl {
      Before("tagExpression") {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectBeforeHookWithOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      Before(42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "", 42)
  }

  @Test
  def testObjectBeforeHookWithTagAndOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      Before("tagExpression", 42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectBeforeStepHook(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep { _: Scenario =>
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
  def testObjectBeforeStepHookNoArg(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "", 1000)
  }

  @Test
  def testObjectBeforeStepHookWithTagNoArg(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep("tagExpression") {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectBeforeStepHookWithOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep(42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "", 42)
  }

  @Test
  def testObjectBeforeStepHookWithTagAndOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep("tagExpression", 42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "tagExpression", 42)
  }


  @Test
  def testObjectAfterHook(): Unit = {

    object Glue extends ScalaDsl {
      After { _: Scenario =>
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
  def testObjectAfterHookNoArg(): Unit = {

    object Glue extends ScalaDsl {
      After {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "", 1000)
  }

  @Test
  def testObjectAfterHookWithTagNoArg(): Unit = {

    object Glue extends ScalaDsl {
      After("tagExpression") {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectAfterHookWithOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      After(42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "", 42)
  }

  @Test
  def testObjectAfterHookWithTagAndOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      After("tagExpression", 42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectAfterStepHook(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep { _: Scenario =>
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
  def testObjectAfterStepHookNoArg(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "", 1000)
  }

  @Test
  def testObjectAfterStepHookWithTagNoArg(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep("tagExpression") {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectAfterStepHookWithOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep(42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "", 42)
  }

  @Test
  def testObjectAfterStepHookWithTagAndOrderNoArg(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep("tagExpression", 42) {
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "tagExpression", 42)
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

}
