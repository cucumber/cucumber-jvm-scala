package io.cucumber.scala

import io.cucumber.core.backend.DataTableTypeDefinition

trait ScalaDataTableTypeDefinition
    extends DataTableTypeDefinition
    with AbstractDatatableElementTransformerDefinition {}

object ScalaDataTableTypeDefinition {

  def apply[T](
      details: ScalaDataTableTypeDetails[T],
      scenarioScoped: Boolean
  ): ScalaDataTableTypeDefinition = {
    details match {
      case entryDetails @ ScalaDataTableEntryTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableEntryDefinition[T](entryDetails)
        } else {
          new ScalaGlobalDataTableEntryDefinition[T](entryDetails)
        }
      case entryDetails @ ScalaDataTableOptionalEntryTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableOptionalEntryDefinition[T](
            entryDetails
          )
        } else {
          new ScalaGlobalDataTableOptionalEntryDefinition[T](entryDetails)
        }
      case rowDetails @ ScalaDataTableRowTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableRowDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableRowDefinition[T](rowDetails)
        }
      case rowDetails @ ScalaDataTableOptionalRowTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableOptionalRowDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableOptionalRowDefinition[T](rowDetails)
        }
      case cellDetails @ ScalaDataTableCellTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableCellDefinition[T](cellDetails)
        } else {
          new ScalaGlobalDataTableCellDefinition[T](cellDetails)
        }
      case cellDetails @ ScalaDataTableOptionalCellTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableOptionalCellDefinition[T](cellDetails)
        } else {
          new ScalaGlobalDataTableOptionalCellDefinition[T](cellDetails)
        }
      case rowDetails @ ScalaDataTableTableTypeDetails(_, _, _) =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableDefinition[T](rowDetails)
        }
    }
  }

}
