package io.cucumber.scala

import io.cucumber.core.backend._
import org.junit.Assert.assertEquals
import org.junit.Test

class ScalaDslDefaultDataTableCellTransformerTest {

  @Test
  def testClassDefaultDataTableCellTransformer(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertClassDefaultDataTableCellTransformer(glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testClassDefaultDataTableCellTransformerWithEmpty(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertClassDefaultDataTableCellTransformer(glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
    assertClassDefaultDataTableCellTransformer(glue.registry.defaultDataTableCellTransformers.head, "[empty]", classOf[StringBuilder], "-class scala.collection.mutable.StringBuilder")
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectDefaultDataTableCellTransformer(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableCellTransformer(Glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testObjectDefaultDataTableCellTransformerWithEmpty(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableCellTransformer(Glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
    assertObjectDefaultDataTableCellTransformer(Glue.registry.defaultDataTableCellTransformers.head, "[empty]", classOf[StringBuilder], "-class scala.collection.mutable.StringBuilder")
  }


  private def assertClassDefaultDataTableCellTransformer(details: ScalaDefaultDataTableCellTransformerDetails, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableCellTransformer(ScalaDefaultDataTableCellTransformerDefinition(details, true), input, toType, expectedOutput)
  }

  private def assertObjectDefaultDataTableCellTransformer(details: ScalaDefaultDataTableCellTransformerDetails, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableCellTransformer(ScalaDefaultDataTableCellTransformerDefinition(details, false), input, toType, expectedOutput)
  }

  private def assertDefaultDataTableCellTransformer(typeDef: DefaultDataTableCellTransformerDefinition, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertEquals(toType, typeDef.tableCellByTypeTransformer().transform(input, toType).getClass)
    assertEquals(expectedOutput.toString, typeDef.tableCellByTypeTransformer().transform(input, toType).toString)
  }

}
