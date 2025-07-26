package io.cucumber.scala

import io.cucumber.core.backend.DataTableTypeDefinition

trait ScalaDataTableTypeDefinition
    extends DataTableTypeDefinition
    with AbstractDatatableElementTransformerDefinition {

  val details: ScalaDataTableTypeDetails[_]

  override val location: StackTraceElement = details.stackTraceElement

  override val emptyPatterns: Seq[String] = details.emptyPatterns

}

object ScalaDataTableTypeDefinition {

  def apply[T](
      details: ScalaDataTableTypeDetails[T],
      scenarioScoped: Boolean
  ): ScalaDataTableTypeDefinition = {
    details match {
      case entryDetails: ScalaDataTableEntryTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableEntryDefinition[T](entryDetails)
        } else {
          new ScalaGlobalDataTableEntryDefinition[T](entryDetails)
        }
      case entryDetails: ScalaDataTableOptionalEntryTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableOptionalEntryDefinition[T](
            entryDetails
          )
        } else {
          new ScalaGlobalDataTableOptionalEntryDefinition[T](entryDetails)
        }
      case rowDetails: ScalaDataTableRowTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableRowDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableRowDefinition[T](rowDetails)
        }
      case rowDetails: ScalaDataTableOptionalRowTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableOptionalRowDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableOptionalRowDefinition[T](rowDetails)
        }
      case cellDetails: ScalaDataTableCellTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableCellDefinition[T](cellDetails)
        } else {
          new ScalaGlobalDataTableCellDefinition[T](cellDetails)
        }
      case cellDetails: ScalaDataTableOptionalCellTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableOptionalCellDefinition[T](cellDetails)
        } else {
          new ScalaGlobalDataTableOptionalCellDefinition[T](cellDetails)
        }
      case rowDetails: ScalaDataTableTableTypeDetails[_] =>
        if (scenarioScoped) {
          new ScalaScenarioScopedDataTableDefinition[T](rowDetails)
        } else {
          new ScalaGlobalDataTableDefinition[T](rowDetails)
        }
    }
  }

}
