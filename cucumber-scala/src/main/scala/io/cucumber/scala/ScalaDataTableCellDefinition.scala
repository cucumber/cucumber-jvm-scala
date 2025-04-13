package io.cucumber.scala

import io.cucumber.datatable.{DataTableType, TableCellTransformer}

trait ScalaDataTableCellDefinition[T] extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableCellTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableCellTransformer[T] = (cell: String) => {
    details.body.transform(replaceEmptyPatternsWithEmptyString(cell))
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

class ScalaScenarioScopedDataTableCellDefinition[T](
    override val details: ScalaDataTableCellTypeDetails[T]
) extends ScalaDataTableCellDefinition[T] {}

class ScalaGlobalDataTableCellDefinition[T](
    override val details: ScalaDataTableCellTypeDetails[T]
) extends ScalaDataTableCellDefinition[T] {}
