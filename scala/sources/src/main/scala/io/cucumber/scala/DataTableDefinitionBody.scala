package io.cucumber.scala

import io.cucumber.datatable.DataTable

trait DataTableEntryDefinitionBody[T] {

  def transform(entry: Map[String, String]): T

}

trait DataTableRowDefinitionBody[T] {

  def transform(row: Seq[String]): T

}

trait DataTableCellDefinitionBody[T] {

  def transform(cell: String): T

}

trait DataTableDefinitionBody[T] {

  def transform(dataTable: DataTable): T

}

object DataTableEntryDefinitionBody {

  import scala.language.implicitConversions

  implicit def function1AsDataTableEntryDefinitionBody[T](f: (Map[String, String]) => T): DataTableEntryDefinitionBody[T] = new DataTableEntryDefinitionBody[T] {
    override def transform(entry: Map[String, String]): T = f.apply(entry)
  }

}

object DataTableRowDefinitionBody {

  import scala.language.implicitConversions

  implicit def function1AsDataTableRowDefinitionBody[T](f: (Seq[String]) => T): DataTableRowDefinitionBody[T] = new DataTableRowDefinitionBody[T] {
    override def transform(row: Seq[String]): T = f.apply(row)
  }

}

object DataTableCellDefinitionBody {

  import scala.language.implicitConversions

  implicit def function1AsDataTableCellDefinitionBody[T](f: (String) => T): DataTableCellDefinitionBody[T] = new DataTableCellDefinitionBody[T] {
    override def transform(cell: String): T = f.apply(cell)
  }

}

object DataTableDefinitionBody {

  import scala.language.implicitConversions

  implicit def function1AsDataTableDefinitionBody[T](f: (DataTable) => T): DataTableDefinitionBody[T] = new DataTableDefinitionBody[T] {
    override def transform(dataTable: DataTable): T = f.apply(dataTable)
  }

}
