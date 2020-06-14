package tests.datatables

import io.cucumber.scala.{EN, ScalaDsl}
import java.util.{List => JList, Map => JMap}

import io.cucumber.scala.Implicits._
import io.cucumber.datatable.DataTable

import scala.jdk.CollectionConverters._


class DataTableTypeSteps extends ScalaDsl with EN {

  case class GroupOfAuthor(authors: Seq[Author])

  case class GroupOfAuthorWithEmpty(authors: Seq[Author])

  case class Author(name: String, surname: String, famousBook: String)

  case class AuthorWithEmpty(name: String, surname: String, famousBook: String) {
    def toAuthor: Author = Author(name, surname, famousBook)
  }

  case class AuthorRow(name: String, surname: String, famousBook: String) {
    def toAuthor: Author = Author(name, surname, famousBook)
  }

  case class AuthorRowWithEmpty(name: String, surname: String, famousBook: String) {
    def toAuthor: Author = Author(name, surname, famousBook)
  }

  case class AuthorCell(cell: String)

  case class AuthorCellWithEmpty(cell: String)

  var _authors: Seq[Author] = _
  var _names: String = _

  DataTableType { entry: Map[String, String] =>
    Author(entry("name"), entry("surname"), entry("famousBook"))
  }

  DataTableType("[empty]") { (entry: Map[String, String]) =>
    AuthorWithEmpty(entry("name"), entry("surname"), entry("famousBook"))
  }

  DataTableType { row: Seq[String] =>
    AuthorRow(row(0), row(1), row(2))
  }

  DataTableType("[empty]") {(row: Seq[String]) =>
    AuthorRowWithEmpty(row(0), row(1), row(2))
  }

  DataTableType { cell: String =>
    AuthorCell(cell)
  }

  DataTableType("[empty]") { (cell: String) =>
    AuthorCellWithEmpty(cell)
  }

  DataTableType { table: DataTable =>
    val authors = table.asMaps().asScala
      .map(_.asScala)
      .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
      .toSeq
    GroupOfAuthor(authors)
  }

  DataTableType("[empty]") {(table: DataTable) =>
    val authors = table.asMaps().asScala
      .map(_.asScala)
      .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
      .toSeq
    GroupOfAuthorWithEmpty(authors)
  }

  Given("the following authors as entries") { (authors: JList[Author]) =>
    _authors = authors
      .asScala
      .toSeq
  }

  Given("the following authors as entries with empty") { (authors: JList[AuthorWithEmpty]) =>
    _authors = authors
      .asScala
      .toSeq
      .map(_.toAuthor)
  }

  Given("the following authors as entries with empty, as table") { (authors: DataTable) =>
    _authors = authors
      .asScalaRawList[AuthorWithEmpty]
      .map(_.toAuthor)
  }

  Given("the following authors as rows") { (authors: JList[AuthorRow]) =>
    _authors = authors
      .asScala
      .toSeq
      .map(_.toAuthor)
  }

  Given("the following authors as rows with empty") { (authors: JList[AuthorRowWithEmpty]) =>
    _authors = authors
      .asScala
      .toSeq
      .map(_.toAuthor)
  }

  Given("the following authors as rows with empty, as table") { (authors: DataTable) =>
    _authors = authors
      .asScalaRawList[AuthorRowWithEmpty]
      .map(_.toAuthor)
  }

  Given("the following authors as cells") { (authors: JList[JList[AuthorCell]]) =>
    _authors = authors
      .asScala
      .map(_.asScala)
      .toSeq
      .map(line => Author(line(0).cell, line(1).cell, line(2).cell))
  }

  Given("the following authors as cells with empty") { (authors: JList[JList[AuthorCellWithEmpty]]) =>
    _authors = authors
      .asScala
      .map(_.asScala)
      .toSeq
      .map(line => Author(line(0).cell, line(1).cell, line(2).cell))
  }

  Given("the following authors as cells with empty, as map") { (authors: JList[JMap[String, AuthorCellWithEmpty]]) =>
    _authors = authors
      .asScala
      .toSeq
      .map(_.asScala)
      .map(line => Author(line("name").cell, line("surname").cell, line("famousBook").cell))
  }

  Given("the following authors as cells with empty, as table as map") { (authors: DataTable) =>
    _authors = authors
      .asScalaRawMaps[String, AuthorCellWithEmpty]
      .map(line => Author(line("name").cell, line("surname").cell, line("famousBook").cell))
  }

  Given("the following authors as cells with empty, as table as list") { (authors: DataTable) =>
    _authors = authors
      .asScalaRawLists[AuthorCellWithEmpty]
      .map(line => Author(line(0).cell, line(1).cell, line(2).cell))
  }

  Given("the following authors as table") { (authors: DataTable) =>
    _authors = authors.convert[GroupOfAuthor](classOf[GroupOfAuthor], false).authors
  }

  Given("the following authors as table with empty") { (authors: DataTable) =>
    _authors = authors.convert[GroupOfAuthorWithEmpty](classOf[GroupOfAuthorWithEmpty], false).authors
  }

  When("I concat their names") {
    _names = _authors.map(_.name).mkString(",")
  }

  Then("""I get {string}""") { expected: String =>
    assert(_names == expected)
  }

}
