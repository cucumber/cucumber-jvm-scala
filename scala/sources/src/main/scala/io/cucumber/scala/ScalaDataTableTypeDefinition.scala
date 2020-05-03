package io.cucumber.scala

import io.cucumber.core.backend.DataTableTypeDefinition

trait ScalaDataTableTypeDefinition
  extends DataTableTypeDefinition
    with AbstractDatatableElementTransformerDefinition {

}

object ScalaDataTableTypeDefinition {

  def apply[T](details: ScalaDataTableTypeDetails[T], scenarioScoped: Boolean): ScalaDataTableTypeDefinition = {
    details match {
      case entryDetails@ScalaDataTableEntryTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableEntryDefinition[T](entryDetails)
        } else {
          new ScalaGlobalDataTableEntryDefinition[T](entryDetails)
        }
      case rowDetails@ScalaDataTableRowTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableRowDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableRowDefinition[T](rowDetails)
        }
      case rowDetails@ScalaDataTableCellTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableCellDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableCellDefinition[T](rowDetails)
        }
      case rowDetails@ScalaDataTableTableTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableDefinition[T](rowDetails)
        }
    }
  }

}