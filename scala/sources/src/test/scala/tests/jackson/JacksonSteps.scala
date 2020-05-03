package tests.jackson

import io.cucumber.scala.{EN, JacksonDefaultDataTableEntryTransformer, ScalaDsl}

import scala.collection.JavaConverters._

case class MyCaseClass(field1: Double, field2: Boolean, field3: String)

class JacksonSteps extends ScalaDsl with EN with JacksonDefaultDataTableEntryTransformer {

  override def emptyStringReplacement: String = "[blank]"

  Given("I have the following datatable") { (data: java.util.List[MyCaseClass]) =>
    val expected = Seq(
      MyCaseClass(1.2, true, "abc"),
      MyCaseClass(2.3, false, "def"),
      MyCaseClass(3.4, true, "ghj")
    )
    assert(data.asScala == expected)
  }

  Given("I have the following datatable, with an empty value") { (data: java.util.List[MyCaseClass]) =>
    val expected = Seq(
      MyCaseClass(1.2, true, "abc"),
      MyCaseClass(2.3, false, ""),
      MyCaseClass(3.4, true, "ghj")
    )
    assert(data.asScala == expected)
  }

}
