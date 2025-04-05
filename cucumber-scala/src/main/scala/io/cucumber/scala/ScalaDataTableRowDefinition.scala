package io.cucumber.scala

import java.util.{List => JavaList}
import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableRowTransformer}

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

trait ScalaDataTableRowDefinition[T] extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableRowTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

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
