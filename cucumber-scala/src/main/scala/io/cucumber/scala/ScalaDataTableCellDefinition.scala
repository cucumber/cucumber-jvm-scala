package io.cucumber.scala

import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableCellTransformer}

import scala.annotation.nowarn

trait ScalaDataTableCellDefinition[T] extends ScalaDataTableTypeDefinition {

  override val details: ScalaDataTableCellTypeDetails[T]

  private val transformer: TableCellTransformer[T] = (cell: String) => {
    details.body.transform(replaceEmptyPatternsWithEmptyString(cell))
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

@nowarn
class ScalaScenarioScopedDataTableCellDefinition[T](
    override val details: ScalaDataTableCellTypeDetails[T]
) extends ScalaDataTableCellDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDataTableCellDefinition[T](
    override val details: ScalaDataTableCellTypeDetails[T]
) extends ScalaDataTableCellDefinition[T] {}
