package io.cucumber.scala

import io.cucumber.core.backend.{HookDefinition, ScenarioScoped, TestCaseState}

trait ScalaHookDefinition extends HookDefinition with AbstractGlueDefinition {

  val hookDetails: ScalaHookDetails

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  override def execute(state: TestCaseState): Unit = {
    executeAsCucumber(hookDetails.body.apply(new Scenario(state)))
  }

  override def getTagExpression: String = hookDetails.tagExpression

  override def getOrder: Int = hookDetails.order

}

object ScalaHookDefinition {

  def apply(scalaHookDetails: ScalaHookDetails, scenarioScoped: Boolean): ScalaHookDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedHookDefinition(scalaHookDetails)
    } else {
      new ScalaGlobalHookDefinition(scalaHookDetails)
    }
  }

}

class ScalaScenarioScopedHookDefinition(override val hookDetails: ScalaHookDetails) extends ScalaHookDefinition with ScenarioScoped {
}

class ScalaGlobalHookDefinition(override val hookDetails: ScalaHookDetails) extends ScalaHookDefinition {
}
