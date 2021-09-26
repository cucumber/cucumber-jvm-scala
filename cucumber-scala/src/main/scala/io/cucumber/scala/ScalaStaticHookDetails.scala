package io.cucumber.scala

import io.cucumber.scala.Aliases.StaticHookDefinitionBody

case class ScalaStaticHookDetails(
    order: Int,
    body: StaticHookDefinitionBody,
    stackTraceElement: StackTraceElement
)
