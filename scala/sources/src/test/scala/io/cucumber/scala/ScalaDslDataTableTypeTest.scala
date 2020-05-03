package io.cucumber.scala

import io.cucumber.datatable.DataTable
import io.cucumber.scala.ScalaDslDataTableTypeTest.{Author, Cell, GroupOfAuthor}
import org.junit.Assert.assertEquals
import org.junit.Test

import scala.collection.JavaConverters._

object ScalaDslDataTableTypeTest {

  private case class GroupOfAuthor(authors: Seq[Author])

  private case class Author(name: String, surname: String, famousBook: String)

  private case class Cell(cell: String)

  val DATATABLE: Seq[Seq[String]] = Seq(
    Seq("name", "surname", "famousBook"),
    Seq("Alan", "Alou", "The Lion King"),
    Seq("Robert", "Bob", "Le Petit Prince")
  )

  val DATATABLE_WITH_EMPTY: Seq[Seq[String]] = Seq(
    Seq("name", "surname", "famousBook"),
    Seq("Alan", "Alou", "The Lion King"),
    Seq("Robert", "[empty]", "Le Petit Prince")
  )

}

class ScalaDslDataTableTypeTest {

  @Test
  def testDataTableEntryType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { entry: Map[String, String] =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE, expected)
  }

  @Test
  def testDataTableEntryTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (entry: Map[String, String]) =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY, expected)
  }

  @Test
  def testDataTableRowType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { row: Seq[String] =>
        Author(row(0), row(1), row(2))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testDataTableRowTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (row: Seq[String]) =>
        Author(row(0), row(1), row(2))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testDataTableCellType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { cell: String =>
        Cell(cell)
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell("Bob"), Cell("Le Petit Prince")).asJava
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testDataTableCellTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (cell: String) =>
        Cell(cell)
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell(""), Cell("Le Petit Prince")).asJava
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testClassDataTableTableType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { table: DataTable =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val glue = new Glue()

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ))
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE, expected)
  }

  @Test
  def testClassDataTableTableTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (table: DataTable) =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val glue = new Glue()

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ))
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY, expected)
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectDataTableEntryType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { entry: Map[String, String] =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE, expected)
  }

  @Test
  def testObjectDataTableEntryTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (entry: Map[String, String]) =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY, expected)
  }

  @Test
  def testObjectDataTableRowType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { row: Seq[String] =>
        Author(row(0), row(1), row(2))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testObjectDataTableRowTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (row: Seq[String]) =>
        Author(row(0), row(1), row(2))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testObjectDataTableCellType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { cell: String =>
        Cell(cell)
      }
    }

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell("Bob"), Cell("Le Petit Prince")).asJava
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testObjectDataTableCellTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (cell: String) =>
        Cell(cell)
      }
    }

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell(""), Cell("Le Petit Prince")).asJava
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testObjectDataTableTableType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { table: DataTable =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ))
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslDataTableTypeTest.DATATABLE, expected)
  }

  @Test
  def testObjectDataTableTableTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (table: DataTable) =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ))
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslDataTableTypeTest.DATATABLE_WITH_EMPTY, expected)
  }

  private def assertClassDataTableType(details: ScalaDataTableTypeDetails[_], emptyPatterns: Seq[String], dataTable: Seq[Seq[String]], expectedObj: Any): Unit = {
    assertDataTableType(ScalaDataTableTypeDefinition(details, true), emptyPatterns, dataTable, expectedObj)
  }

  private def assertObjectDataTableType(details: ScalaDataTableTypeDetails[_], emptyPatterns: Seq[String], dataTable: Seq[Seq[String]], expectedObj: Any): Unit = {
    assertDataTableType(ScalaDataTableTypeDefinition(details, false), emptyPatterns, dataTable, expectedObj)
  }

  private def assertDataTableType(dataTableType: ScalaDataTableTypeDefinition, emptyPatterns: Seq[String], dataTable: Seq[Seq[String]], expectedObj: Any): Unit = {
    assertEquals(emptyPatterns, dataTableType.emptyPatterns)

    val obj = dataTableType.dataTableType.transform(dataTable.map(_.asJava).asJava)
    assertEquals(expectedObj, obj)
  }

}
