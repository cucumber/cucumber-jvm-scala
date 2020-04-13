package io.cucumber.scala

import io.cucumber.core.backend.Glue

class GlueAdaptor(glue: Glue) {

  def addDefinition(registry: ScalaDslRegistry): Unit = {
    registry.stepDefinitions.foreach(glue.addStepDefinition)
    registry.beforeHooks.foreach(glue.addBeforeHook)
    registry.afterHooks.foreach(glue.addAfterHook)
    registry.afterStepHooks.foreach(glue.addAfterStepHook)
    registry.beforeStepHooks.foreach(glue.addBeforeStepHook)
  }

}
