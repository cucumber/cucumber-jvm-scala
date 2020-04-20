package io.cucumber.scala

import io.cucumber.core.backend.CucumberInvocationTargetException
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import scala.util.Try

class ScalaStepDefinitionTest {

  @Test
  def can_define_step() = {
    var args: List[Any] = List()
    val body = (params: List[Any]) => {
      args = params
    }
    val definition = new ScalaStepDefinition(null, "whatever", "three (.*) mice", Array(classOf[String]), body)
    definition.execute(Array("one_string_argument"))

    assertEquals("one_string_argument", args.head)
  }

  @Test
  def can_provide_location_of_step() = {
    val body = (_: List[Any]) => {
      throw new PendingException()
    }
    val frame = Utils.frame(this)
    val definition = new ScalaStepDefinition(frame, "whatever", "three (.*) mice", Array(), body)

    val tried = Try(definition.execute(Array()))

    assertTrue(tried.isFailure)
    val ex = tried.failed.get
    assertTrue(ex.isInstanceOf[CucumberInvocationTargetException])
    val matched = ex.asInstanceOf[CucumberInvocationTargetException].getInvocationTargetExceptionCause.getStackTrace.filter(definition.isDefinedAt).head

    // Method name in Scala does not make much sense
    //assertEquals("$anonfun$can_provide_location_of_step$1", matched.getMethodName)
    //assertEquals(classOf[ScalaStepDefinitionTest].getName, matched.getClassName)
    assertEquals("ScalaStepDefinitionTest.scala", matched.getFileName)
    assertEquals(27, matched.getLineNumber)
  }

}
