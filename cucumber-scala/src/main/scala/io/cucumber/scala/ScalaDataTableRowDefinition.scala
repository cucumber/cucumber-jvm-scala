package io.cucumber.scala

import java.util.{List => JavaList}
import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableRowTransformer}

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

trait ScalaDataTableRowDefinition[T] extends ScalaDataTableTypeDefinition {

  override val details: ScalaDataTableRowTypeDetails[T]

  private val transformer: TableRowTransformer[T] = (row: JavaList[String]) => {
    details.body.transform(
      replaceEmptyPatternsWithEmptyString(row.asScala.toSeq)
    )
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

@nowarn
class ScalaScenarioScopedDataTableRowDefinition[T](
    override val details: ScalaDataTableRowTypeDetails[T]
) extends ScalaDataTableRowDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDataTableRowDefinition[T](
    override val details: ScalaDataTableRowTypeDetails[T]
) extends ScalaDataTableRowDefinition[T] {}
