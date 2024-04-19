package io.cucumber.scala

import io.cucumber.core.backend.Glue

class GlueAdaptor(glue: Glue) {

  /** Load the step definitions and hooks from a ScalaDsl instance into the
    * glue.
    *
    * @param registry
    *   ScalaDsl instance registry
    * @param scenarioScoped
    *   true for class instances, false for object singletons
    */
  def loadRegistry(
      registry: ScalaDslRegistry,
      scenarioScoped: Boolean
  ): Unit = {

    // If the registry is not consistent, this indicates a mistake in the users definition and we want to let him know.
    registry.checkConsistency(scenarioScoped).left.foreach {
      (ex: IncorrectHookDefinitionException) =>
        throw ex
    }

    registry.stepDefinitions
      .map(ScalaStepDefinition(_, scenarioScoped))
      .foreach(glue.addStepDefinition)

    // The presence of beforeAll/afterAll hooks with scenarioScoped is checked by checkConsistency above
    if (!scenarioScoped) {
      registry.beforeAllHooks
        .map(ScalaStaticHookDefinition(_))
        .foreach(glue.addBeforeAllHook)
      registry.afterAllHooks
        .map(ScalaStaticHookDefinition(_))
        .foreach(glue.addAfterAllHook)
    }

    registry.beforeHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(glue.addBeforeHook)
    registry.afterHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(glue.addAfterHook)
    registry.beforeStepHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(glue.addBeforeStepHook)
    registry.afterStepHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(glue.addAfterStepHook)

    registry.docStringTypes
      .map(ScalaDocStringTypeDefinition(_, scenarioScoped))
      .foreach(glue.addDocStringType)
    registry.dataTableTypes
      .map(ScalaDataTableTypeDefinition(_, scenarioScoped))
      .foreach(glue.addDataTableType)
    registry.parameterTypes
      .map(ScalaParameterTypeDefinition(_, scenarioScoped))
      .foreach(glue.addParameterType)

    registry.defaultParameterTransformers
      .map(ScalaDefaultParameterTransformerDefinition(_, scenarioScoped))
      .foreach(glue.addDefaultParameterTransformer)
    registry.defaultDataTableCellTransformers
      .map(ScalaDefaultDataTableCellTransformerDefinition(_, scenarioScoped))
      .foreach(glue.addDefaultDataTableCellTransformer)
    registry.defaultDataTableEntryTransformers
      .map(ScalaDefaultDataTableEntryTransformerDefinition(_, scenarioScoped))
      .foreach(glue.addDefaultDataTableEntryTransformer)
  }

}
