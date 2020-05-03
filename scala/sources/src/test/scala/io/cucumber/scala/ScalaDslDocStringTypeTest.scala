package io.cucumber.scala

import io.cucumber.core.backend._
import org.junit.Test


class ScalaDslDocStringTypeTest {

  @Test
  def testDocStringType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DocStringType("doc") { docString =>
        new StringBuilder(docString)
      }
    }

    val glue = new Glue()

    assertClassDocStringType(glue.registry.docStringTypes.head)
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectDocStringType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DocStringType("doc") { docString =>
        new StringBuilder(docString)
      }
    }

    assertObjectDocStringType(Glue.registry.docStringTypes.head)
  }

  private def assertClassDocStringType(details: ScalaDocStringTypeDetails[_]): Unit = {
    assertDocStringType(ScalaDocStringTypeDefinition(details, true))
  }

  private def assertObjectDocStringType(details: ScalaDocStringTypeDetails[_]): Unit = {
    assertDocStringType(ScalaDocStringTypeDefinition(details, false))
  }

  private def assertDocStringType(docStringType: DocStringTypeDefinition): Unit = {
    // Cannot assert much because everything is strangely private in DocStringTypeDefinition
    // Real feature tests will do the job
  }

}
