package io.cucumber.scala

import java.util.{List => JavaList}

import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableRowTransformer}

import scala.jdk.CollectionConverters._

trait ScalaDataTableRowDefinition[T] extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableRowTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableRowTransformer[T] = new TableRowTransformer[T] {
    override def transform(row: JavaList[String]): T = {
      details.body.transform(replaceEmptyPatternsWithEmptyString(row.asScala.toSeq))
    }
  }

  override val dataTableType = new DataTableType(details.tag.runtimeClass, transformer)

}

class ScalaScenarioScopedDataTableRowDefinition[T](override val details: ScalaDataTableRowTypeDetails[T]) extends ScalaDataTableRowDefinition[T] with ScenarioScoped {
}

class ScalaGlobalDataTableRowDefinition[T](override val details: ScalaDataTableRowTypeDetails[T]) extends ScalaDataTableRowDefinition[T] {
}
