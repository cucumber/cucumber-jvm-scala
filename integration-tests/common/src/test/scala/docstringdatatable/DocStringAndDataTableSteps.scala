package docstringdatatable

import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}

import java.util.{List => JList}
import scala.jdk.CollectionConverters._

class DocStringAndDataTableSteps extends ScalaDsl with EN {

  Given("a step with a data table and a doc string") {
    (table: DataTable, doc: String) =>
      assert(table.height() == 1 && table.width() == 1)
      assert(table.cell(0, 0) == "hello")
      assert(doc == "world")
  }

  Given("a step with a doc string and a data table") {
    (doc: String, table: DataTable) =>
      assert(doc == "hello")
      assert(table.height() == 1 && table.width() == 1)
      assert(table.cell(0, 0) == "world")
  }

  case class Person(name: String)

  DataTableType { (row: Map[String, String]) =>
    Person(row("name"))
  }

  Given("a step with a list of persons and a doc string") {
    (persons: JList[Person], doc: String) =>
      assert(persons.asScala.toList == List(Person("Alice"), Person("Bob")))
      assert(doc == "world")
  }

}
