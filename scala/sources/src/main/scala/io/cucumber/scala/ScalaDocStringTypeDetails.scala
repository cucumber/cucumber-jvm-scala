package io.cucumber.scala

import io.cucumber.scala.Aliases.DocStringDefinitionBody

import scala.reflect.ClassTag

case class ScalaDocStringTypeDetails[T](contentType: String, body: DocStringDefinitionBody[T], tag: ClassTag[T])
