package io.cucumber.scala

import io.cucumber.datatable.{DataTableType, TableCellTransformer}

trait ScalaDataTableOptionalCellDefinition[T]
    extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableOptionalCellTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableCellTransformer[T] = (cell: String) => {
    details.body.transform(Option(replaceEmptyPatternsWithEmptyString(cell)))
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

class ScalaScenarioScopedDataTableOptionalCellDefinition[T](
    override val details: ScalaDataTableOptionalCellTypeDetails[T]
) extends ScalaDataTableOptionalCellDefinition[T] {}

class ScalaGlobalDataTableOptionalCellDefinition[T](
    override val details: ScalaDataTableOptionalCellTypeDetails[T]
) extends ScalaDataTableOptionalCellDefinition[T] {}
