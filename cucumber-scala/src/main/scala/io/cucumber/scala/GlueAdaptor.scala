package io.cucumber.scala

import io.cucumber.core.backend.{CucumberBackendException, Glue}

import scala.collection.mutable.ArrayBuffer

class GlueAdaptor(glue: Glue) {

  private val definitions: ArrayBuffer[AbstractGlueDefinition] = ArrayBuffer()
  private var registered: Int = 0
  private var expectedRegistrations: Int = -1

  def startRegistration(): Unit = {
    registered = 0
  }

  def finishRegistration(): Unit = {
    if (expectedRegistrations < 0) {
      expectedRegistrations = registered
    } else if (expectedRegistrations != registered) {
      throw new CucumberBackendException(
        s"""Found an inconsistent number of glue registrations.
            |Previously $expectedRegistrations step definitions, hooks and parameter types were registered. Currently $registered.
            |To optimize performance Cucumber expects glue registration to be identical for each scenario and example.""".stripMargin
      )
    }
  }

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
      .foreach(df => updateOrRegister(df, glue.addStepDefinition))

    // The presence of beforeAll/afterAll hooks with scenarioScoped is checked by checkConsistency above
    if (!scenarioScoped) {
      registry.beforeAllHooks
        .map(ScalaStaticHookDefinition(_))
        .foreach(df => updateOrRegister(df, glue.addBeforeAllHook))
      registry.afterAllHooks
        .map(ScalaStaticHookDefinition(_))
        .foreach(df => updateOrRegister(df, glue.addAfterAllHook))
    }

    registry.beforeHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addBeforeHook))
    registry.afterHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addAfterHook))
    registry.beforeStepHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addBeforeStepHook))
    registry.afterStepHooks
      .map(ScalaHookDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addAfterStepHook))

    registry.docStringTypes
      .map(ScalaDocStringTypeDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addDocStringType))
    registry.dataTableTypes
      .map(ScalaDataTableTypeDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addDataTableType))
    registry.parameterTypes
      .map(ScalaParameterTypeDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addParameterType))

    registry.defaultParameterTransformers
      .map(ScalaDefaultParameterTransformerDefinition(_, scenarioScoped))
      .foreach(df => updateOrRegister(df, glue.addDefaultParameterTransformer))
    registry.defaultDataTableCellTransformers
      .map(ScalaDefaultDataTableCellTransformerDefinition(_, scenarioScoped))
      .foreach(df =>
        updateOrRegister(df, glue.addDefaultDataTableCellTransformer)
      )
    registry.defaultDataTableEntryTransformers
      .map(ScalaDefaultDataTableEntryTransformerDefinition(_, scenarioScoped))
      .foreach(df =>
        updateOrRegister(df, glue.addDefaultDataTableEntryTransformer)
      )
  }

  private def updateOrRegister[T <: AbstractGlueDefinition](
      candidate: T,
      register: T => Unit
  ): Unit = {
    if (definitions.size <= registered) {
      definitions.addOne(candidate)
      register.apply(candidate)
    } else {
      val existing: AbstractGlueDefinition = definitions(registered)
      requireSameGlueClass(existing, candidate)
      println("BOOM should update closure")
      // TODO
    }
    registered = registered + 1
  }

  private def requireSameGlueClass[T <: AbstractGlueDefinition](
      existing: AbstractGlueDefinition,
      candidate: AbstractGlueDefinition
  ): Unit = {
    if (existing.getClass != candidate.getClass) {
      throw new CucumberBackendException(
        s"""Found an inconsistent glue registrations.
          |Previously the registration in slot $registered was a '${existing.getClass.getName}'. Currently '${candidate.getClass.getName}'.
          |To optimize performance Cucumber expects glue registration to be identical for each scenario and example."""".stripMargin
      )
    }
  }

//  private[scala] def disposeClosures(): Unit = {
//    definitions.foreach(_.disposeClosure)
//  }

}
