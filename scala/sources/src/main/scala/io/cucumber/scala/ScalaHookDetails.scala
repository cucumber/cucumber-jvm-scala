package io.cucumber.scala

import io.cucumber.scala.Aliases.HookBody

case class ScalaHookDetails(tagExpression: String, order: Int, body: HookBody)
