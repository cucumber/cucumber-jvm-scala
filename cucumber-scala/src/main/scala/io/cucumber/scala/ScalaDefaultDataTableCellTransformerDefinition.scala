package io.cucumber.scala

import io.cucumber.core.backend.DefaultDataTableCellTransformerDefinition
import io.cucumber.datatable.TableCellByTypeTransformer

import java.lang.reflect.Type

trait ScalaDefaultDataTableCellTransformerDefinition
    extends DefaultDataTableCellTransformerDefinition
    with AbstractDatatableElementTransformerDefinition {

  val details: ScalaDefaultDataTableCellTransformerDetails

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  override val tableCellByTypeTransformer: TableCellByTypeTransformer =
    (fromValue: String, toTypeValue: Type) => {
      details.body.apply(
        replaceEmptyPatternsWithEmptyString(fromValue),
        toTypeValue
      )
    }

}

object ScalaDefaultDataTableCellTransformerDefinition {

  def apply(
      details: ScalaDefaultDataTableCellTransformerDetails,
      scenarioScoped: Boolean
  ): ScalaDefaultDataTableCellTransformerDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedDataTableCellTransformerDefinition(details)
    } else {
      new ScalaGlobalDataTableCellTransformerDefinition(details)
    }
  }

}

class ScalaScenarioScopedDataTableCellTransformerDefinition(
    override val details: ScalaDefaultDataTableCellTransformerDetails
) extends ScalaDefaultDataTableCellTransformerDefinition {}

class ScalaGlobalDataTableCellTransformerDefinition(
    override val details: ScalaDefaultDataTableCellTransformerDetails
) extends ScalaDefaultDataTableCellTransformerDefinition {}
