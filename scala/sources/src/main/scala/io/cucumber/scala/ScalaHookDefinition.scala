package io.cucumber.scala

import io.cucumber.core.backend.{HookDefinition, TestCaseState}
import io.cucumber.scala.Aliases.HookBody

class ScalaHookDefinition(tagExpression: String, order: Int, body: HookBody)
  extends AbstractGlueDefinition(new Exception().getStackTrace()(3))
    with HookDefinition {

  override def execute(state: TestCaseState): Unit = {
    body(new Scenario(state))
  }

  override def getTagExpression: String = tagExpression

  override def getOrder: Int = order

}
