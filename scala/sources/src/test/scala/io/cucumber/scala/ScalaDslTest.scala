package io.cucumber.scala

import java.net.URI

import io.cucumber.core.backend.TestCaseState
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.Test

class ScalaDslTest {

  val testCaseTest: TestCaseState = new TestCaseState {
    override def getSourceTagNames = null

    override def getStatus = null

    override def isFailed = false

    override def embed(p1: Array[Byte], p2: String) {}

    override def embed(p1: Array[Byte], p2: String, p3: String) {}

    override def write(p1: String) {}

    override def getName = ""

    override def getId = ""

    override def getUri = new URI("classpath:plop")

    override def getLine: Integer = null
  }

  @Test
  def emptyBefore {

    var actualScenario: Scenario = null

    object Befores extends ScalaDsl with EN {
      Before {
        actualScenario = _
      }
    }

    assertEquals(1, Befores.registry.beforeHooks.size)

    val hook = Befores.registry.beforeHooks.head
    hook.execute(testCaseTest)

    assertEquals(testCaseTest, actualScenario.delegate)
    assertEquals(1000, hook.getOrder)
    assertEquals("", hook.getTagExpression)
  }

  @Test
  def taggedBefore {
    var actualScenario: Scenario = null

    object Befores extends ScalaDsl with EN {
      Before("(@foo or @bar) and @zap") {
        actualScenario = _
      }
    }

    assertEquals(1, Befores.registry.beforeHooks.size)

    val hook = Befores.registry.beforeHooks.head
    hook.execute(testCaseTest)

    assertEquals(testCaseTest, actualScenario.delegate)
    assertEquals(1000, hook.getOrder)
    assertEquals("(@foo or @bar) and @zap", hook.getTagExpression)
  }

  @Test
  def orderedBefore {

    object Befores extends ScalaDsl with EN {
      Before(10) { scenario: Scenario => }
    }

    val hook = Befores.registry.beforeHooks(0)
    assertEquals(10, hook.getOrder)
    assertEquals("", hook.getTagExpression)
  }

  @Test
  def taggedOrderedBefore {

    object Befores extends ScalaDsl with EN {
      Before("(@foo or @bar) and @zap", 10) { scenario: Scenario => }
    }

    val hook = Befores.registry.beforeHooks(0)
    assertEquals(10, hook.getOrder)
    assertEquals("(@foo or @bar) and @zap", hook.getTagExpression)
  }

  @Test
  def emptyAfter {

    var actualScenario: Scenario = null

    object Afters extends ScalaDsl with EN {
      After {
        actualScenario = _
      }
    }

    assertEquals(1, Afters.registry.afterHooks.size)

    val hook = Afters.registry.afterHooks.head
    hook.execute(testCaseTest)

    assertEquals(testCaseTest, actualScenario.delegate)
    assertEquals(1000, hook.getOrder)
    assertEquals("", hook.getTagExpression)
  }

  @Test
  def taggedAfter {
    var actualScenario: Scenario = null

    object Afters extends ScalaDsl with EN {
      After("(@foo or @bar) and @zap") {
        actualScenario = _
      }
    }

    assertEquals(1, Afters.registry.afterHooks.size)

    val hook = Afters.registry.afterHooks.head
    hook.execute(testCaseTest)

    assertEquals(testCaseTest, actualScenario.delegate)
    assertEquals(1000, hook.getOrder)
    assertEquals("(@foo or @bar) and @zap", hook.getTagExpression)
  }

  @Test
  def noArg {
    var called = false

    object Dummy extends ScalaDsl with EN {
      // One line to avoid difference in lineNumber between Scala versions
      Given("x") { called = true }
    }

    assertEquals(1, Dummy.registry.stepDefinitions.size)

    val step = Dummy.registry.stepDefinitions.head

    assertEquals("ScalaDslTest.scala:145", step.getLocation) // be careful with formatting or this test will break
    assertEquals("x", step.getPattern)

    step.execute(Array())

    assertTrue(called)
  }

  @Test
  def args {
    var thenumber = 0
    var thecolour = ""

    object Dummy extends ScalaDsl with EN {
      Given("Oh boy, (\\d+) (\\s+) cukes") { (num: Int, colour: String) =>
        thenumber = num
        thecolour = colour
      }
    }

    assertEquals(1, Dummy.registry.stepDefinitions.size)

    val step = Dummy.registry.stepDefinitions.head
    step.execute(Array(new java.lang.Integer(5), "green"))

    assertEquals(5, thenumber)
    assertEquals("green", thecolour)
  }

}
