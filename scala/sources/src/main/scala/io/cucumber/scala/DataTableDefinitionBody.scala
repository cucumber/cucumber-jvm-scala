package io.cucumber.scala

import io.cucumber.datatable.DataTable

trait DataTableEntryDefinitionBody[T] {

  def transform(entry: Map[String, String]): T

}

trait DataTableOptionalEntryDefinitionBody[T] {

  def transform(entry: Map[String, Option[String]]): T

}

trait DataTableRowDefinitionBody[T] {

  def transform(row: Seq[String]): T

}

trait DataTableOptionalRowDefinitionBody[T] {

  def transform(row: Seq[Option[String]]): T

}

trait DataTableCellDefinitionBody[T] {

  def transform(cell: String): T

}

trait DataTableOptionalCellDefinitionBody[T] {

  def transform(cell: Option[String]): T

}

trait DataTableDefinitionBody[T] {

  def transform(dataTable: DataTable): T

}
