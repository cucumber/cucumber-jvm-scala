package io.cucumber.scala

import io.cucumber.core.backend.{HookDefinition, ScenarioScoped, TestCaseState}
import io.cucumber.scala.ScopedHookType.{AFTER, AFTER_STEP, BEFORE, BEFORE_STEP}

import java.util.Optional
import scala.annotation.nowarn

trait ScalaHookDefinition extends HookDefinition with AbstractGlueDefinition {

  val hookDetails: ScalaHookDetails

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  override def execute(state: TestCaseState): Unit = {
    executeAsCucumber(hookDetails.body.apply(new Scenario(state)))
  }

  override def getTagExpression: String = hookDetails.tagExpression

  override def getOrder: Int = hookDetails.order

  override def getHookType: Optional[HookDefinition.HookType] = {
    val javaHookType = hookDetails.hookType match {
      case BEFORE      => HookDefinition.HookType.BEFORE
      case AFTER       => HookDefinition.HookType.AFTER
      case BEFORE_STEP => HookDefinition.HookType.BEFORE_STEP
      case AFTER_STEP  => HookDefinition.HookType.AFTER_STEP
    }
    Optional.of(javaHookType)
  }

}

object ScalaHookDefinition {

  def apply(
      scalaHookDetails: ScalaHookDetails,
      scenarioScoped: Boolean
  ): ScalaHookDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedHookDefinition(scalaHookDetails)
    } else {
      new ScalaGlobalHookDefinition(scalaHookDetails)
    }
  }

}

@nowarn
class ScalaScenarioScopedHookDefinition(
    override val hookDetails: ScalaHookDetails
) extends ScalaHookDefinition
    with ScenarioScoped {}

class ScalaGlobalHookDefinition(override val hookDetails: ScalaHookDetails)
    extends ScalaHookDefinition {}
