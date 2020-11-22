package io.cucumber.scala

import Aliases.HookDefinitionBody

case class ScalaHookDetails(
    tagExpression: String,
    order: Int,
    body: HookDefinitionBody
)
