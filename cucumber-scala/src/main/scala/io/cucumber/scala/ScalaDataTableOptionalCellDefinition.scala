package io.cucumber.scala

import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableCellTransformer}

import scala.annotation.nowarn

trait ScalaDataTableOptionalCellDefinition[T]
    extends ScalaDataTableTypeDefinition {

  override val details: ScalaDataTableOptionalCellTypeDetails[T]

  private val transformer: TableCellTransformer[T] = (cell: String) => {
    details.body.transform(Option(replaceEmptyPatternsWithEmptyString(cell)))
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

@nowarn
class ScalaScenarioScopedDataTableOptionalCellDefinition[T](
    override val details: ScalaDataTableOptionalCellTypeDetails[T]
) extends ScalaDataTableOptionalCellDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDataTableOptionalCellDefinition[T](
    override val details: ScalaDataTableOptionalCellTypeDetails[T]
) extends ScalaDataTableOptionalCellDefinition[T] {}
