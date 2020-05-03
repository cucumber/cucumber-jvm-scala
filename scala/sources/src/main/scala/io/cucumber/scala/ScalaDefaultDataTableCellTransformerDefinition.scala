package io.cucumber.scala

import java.lang.reflect.Type

import io.cucumber.core.backend.{DefaultDataTableCellTransformerDefinition, ScenarioScoped}
import io.cucumber.datatable.TableCellByTypeTransformer

trait ScalaDefaultDataTableCellTransformerDefinition extends DefaultDataTableCellTransformerDefinition with AbstractDatatableElementTransformerDefinition {

  val details: ScalaDefaultDataTableCellTransformerDetails

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  override val tableCellByTypeTransformer: TableCellByTypeTransformer = new TableCellByTypeTransformer {
    override def transform(fromValue: String, toTypeValue: Type): AnyRef = {
      details.body.apply(replaceEmptyPatternsWithEmptyString(fromValue), toTypeValue)
    }
  }

}

object ScalaDefaultDataTableCellTransformerDefinition {

  def apply(details: ScalaDefaultDataTableCellTransformerDetails, scenarioScoped: Boolean): ScalaDefaultDataTableCellTransformerDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedDataTableCellTransformerDefinition(details)
    } else {
      new ScalaGlobalDataTableCellTransformerDefinition(details)
    }
  }

}

class ScalaScenarioScopedDataTableCellTransformerDefinition(override val details: ScalaDefaultDataTableCellTransformerDetails) extends ScalaDefaultDataTableCellTransformerDefinition with ScenarioScoped {
}

class ScalaGlobalDataTableCellTransformerDefinition(override val details: ScalaDefaultDataTableCellTransformerDetails) extends ScalaDefaultDataTableCellTransformerDefinition {
}
