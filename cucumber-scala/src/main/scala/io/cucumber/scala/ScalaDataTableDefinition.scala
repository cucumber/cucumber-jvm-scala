package io.cucumber.scala

import io.cucumber.datatable.{DataTable, DataTableType, TableTransformer}

trait ScalaDataTableDefinition[T] extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableTableTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableTransformer[T] = (table: DataTable) => {
    details.body.transform(replaceEmptyPatternsWithEmptyString(table))
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

class ScalaScenarioScopedDataTableDefinition[T](
    override val details: ScalaDataTableTableTypeDetails[T]
) extends ScalaDataTableDefinition[T] {}

class ScalaGlobalDataTableDefinition[T](
    override val details: ScalaDataTableTableTypeDetails[T]
) extends ScalaDataTableDefinition[T] {}
