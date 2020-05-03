package io.cucumber.scala

import io.cucumber.core.backend._
import io.cucumber.scala.ScalaDslParameterTypeTest.Point
import org.junit.Assert.assertEquals
import org.junit.Test

import scala.collection.JavaConverters._

object ScalaDslParameterTypeTest {

  private case class Point(x: Int, y: Int)

}


class ScalaDslParameterTypeTest {

  @Test
  def testClassParameterType1(): Unit = {

    class Glue extends ScalaDsl with EN {
      ParameterType("string-builder", ".*") { str =>
        new StringBuilder(str)
      }
    }

    val glue = new Glue()

    assertClassParameterType(glue.registry.parameterTypes.head, "string-builder", Seq(".*"), classOf[StringBuilder])
  }

  @Test
  def testClassParameterType2(): Unit = {

    class Glue extends ScalaDsl with EN {
      ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
        Point(x.toInt, y.toInt)
      }
    }

    val glue = new Glue()

    assertClassParameterType(glue.registry.parameterTypes.head, "coordinates", Seq("(.+),(.+)"), classOf[Point])
  }

  @Test
  def testClassParameterType3(): Unit = {

    class Glue extends ScalaDsl with EN {
      ParameterType("ingredients", "(.+), (.+) and (.+)") { (x, y, z) =>
        s"$x-$y-$z"
      }
    }

    val glue = new Glue()

    assertClassParameterType(glue.registry.parameterTypes.head, "ingredients", Seq("(.+), (.+) and (.+)"), classOf[String])
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectParameterType1(): Unit = {

    object Glue extends ScalaDsl with EN {
      ParameterType("string-builder", ".*") { str =>
        new StringBuilder(str)
      }
    }

    assertObjectParameterType(Glue.registry.parameterTypes.head, "string-builder", Seq(".*"), classOf[StringBuilder])
  }

  @Test
  def testObjectParameterType2(): Unit = {

    object Glue extends ScalaDsl with EN {
      ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
        Point(x.toInt, y.toInt)
      }
    }

    assertObjectParameterType(Glue.registry.parameterTypes.head, "coordinates", Seq("(.+),(.+)"), classOf[Point])
  }

  @Test
  def testObjectParameterType3(): Unit = {

    object Glue extends ScalaDsl with EN {
      ParameterType("ingredients", "(.+), (.+) and (.+)") { (x, y, z) =>
        s"$x-$y-$z"
      }
    }

    assertObjectParameterType(Glue.registry.parameterTypes.head, "ingredients", Seq("(.+), (.+) and (.+)"), classOf[String])
  }

  private def assertClassParameterType(details: ScalaParameterTypeDetails[_], name: String, regexps: Seq[String], expectedType: Class[_]): Unit = {
    assertParameterType(ScalaParameterTypeDefinition(details, true), name, regexps, expectedType)
  }

  private def assertObjectParameterType(details: ScalaParameterTypeDetails[_], name: String, regexps: Seq[String], expectedType: Class[_]): Unit = {
    assertParameterType(ScalaParameterTypeDefinition(details, false), name, regexps, expectedType)
  }

  private def assertParameterType(parameterTypeDef: ParameterTypeDefinition, name: String, regexps: Seq[String], expectedType: Class[_]): Unit = {
    val parameterType = parameterTypeDef.parameterType()

    assertEquals(name, parameterType.getName)
    assertEquals(regexps, parameterType.getRegexps.asScala)
    assertEquals(expectedType, parameterType.getType)

    // Cannot assert more because transform method is private
  }

}
