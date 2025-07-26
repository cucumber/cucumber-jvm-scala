package io.cucumber.scala

import io.cucumber.scala.Aliases.DocStringDefinitionBody

import java.lang.reflect.{Type => JType}

case class ScalaDocStringTypeDetails[T](
    contentType: String,
    body: DocStringDefinitionBody[T],
    `type`: JType,
    stackTraceElement: StackTraceElement
)
