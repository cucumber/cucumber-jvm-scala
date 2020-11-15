package io.cucumber.scala

import io.cucumber.core.backend._
import org.junit.Assert.assertEquals
import org.junit.Test

import scala.jdk.CollectionConverters._


class ScalaDslDefaultDataTableEntryTransformerTest {

  @Test
  def testClassDefaultDataTableEntryTransformer(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertClassDefaultDataTableEntryTransformer(glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "d"), classOf[StringBuilder], "Map(a -> b, c -> d)-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testClassDefaultDataTableEntryTransformerWithEmpty(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertClassDefaultDataTableEntryTransformer(glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "[empty]"), classOf[StringBuilder], "Map(a -> b, c -> )-class scala.collection.mutable.StringBuilder")
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectDefaultDataTableEntryTransformer(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableEntryTransformer(Glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "d"), classOf[StringBuilder], "Map(a -> b, c -> d)-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testObjectDefaultDataTableEntryTransformerWithEmpty(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableEntryTransformer(Glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "[empty]"), classOf[StringBuilder], "Map(a -> b, c -> )-class scala.collection.mutable.StringBuilder")
  }

  private def assertClassDefaultDataTableEntryTransformer(details: ScalaDefaultDataTableEntryTransformerDetails, input: Map[String, String], toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableEntryTransformer(ScalaDefaultDataTableEntryTransformerDefinition(details, true), input, toType, expectedOutput)
  }

  private def assertObjectDefaultDataTableEntryTransformer(details: ScalaDefaultDataTableEntryTransformerDetails, input: Map[String, String], toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableEntryTransformer(ScalaDefaultDataTableEntryTransformerDefinition(details, false), input, toType, expectedOutput)
  }

  private def assertDefaultDataTableEntryTransformer(typeDef: DefaultDataTableEntryTransformerDefinition, input: Map[String, String], toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertEquals(toType, typeDef.tableEntryByTypeTransformer().transform(input.asJava, toType, null).getClass)
    assertEquals(expectedOutput.toString, typeDef.tableEntryByTypeTransformer().transform(input.asJava, toType, null).toString)
  }

}
