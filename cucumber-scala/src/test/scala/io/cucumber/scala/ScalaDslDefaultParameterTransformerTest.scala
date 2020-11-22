package io.cucumber.scala

import io.cucumber.core.backend._
import org.junit.Assert.assertEquals
import org.junit.Test

class ScalaDslDefaultParameterTransformerTest {

  @Test
  def testClassDefaultParameterTransformer(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultParameterTransformer {
        (fromValue: String, toValueType: java.lang.reflect.Type) =>
          new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertClassDefaultParameterTransformer(
      glue.registry.defaultParameterTransformers.head,
      "meat",
      classOf[StringBuilder],
      "meat-class scala.collection.mutable.StringBuilder"
    )
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectDefaultParameterTransformer(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultParameterTransformer {
        (fromValue: String, toValueType: java.lang.reflect.Type) =>
          new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultParameterTransformer(
      Glue.registry.defaultParameterTransformers.head,
      "meat",
      classOf[StringBuilder],
      "meat-class scala.collection.mutable.StringBuilder"
    )
  }

  private def assertClassDefaultParameterTransformer(
      details: ScalaDefaultParameterTransformerDetails,
      input: String,
      toType: java.lang.reflect.Type,
      expectedOutput: AnyRef
  ): Unit = {
    assertDefaultParameterTransformer(
      ScalaDefaultParameterTransformerDefinition(details, true),
      input,
      toType,
      expectedOutput
    )
  }

  private def assertObjectDefaultParameterTransformer(
      details: ScalaDefaultParameterTransformerDetails,
      input: String,
      toType: java.lang.reflect.Type,
      expectedOutput: AnyRef
  ): Unit = {
    assertDefaultParameterTransformer(
      ScalaDefaultParameterTransformerDefinition(details, false),
      input,
      toType,
      expectedOutput
    )
  }

  private def assertDefaultParameterTransformer(
      typeDef: DefaultParameterTransformerDefinition,
      input: String,
      toType: java.lang.reflect.Type,
      expectedOutput: AnyRef
  ): Unit = {
    assertEquals(
      toType,
      typeDef.parameterByTypeTransformer().transform(input, toType).getClass
    )
    assertEquals(
      expectedOutput.toString,
      typeDef.parameterByTypeTransformer().transform(input, toType).toString
    )
  }

}
