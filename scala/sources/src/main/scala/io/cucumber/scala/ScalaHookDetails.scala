package io.cucumber.scala

import io.cucumber.scala.Aliases.HookDefinitionBody

case class ScalaHookDetails(tagExpression: String, order: Int, body: HookDefinitionBody)
