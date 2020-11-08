package io.cucumber.scala

import Aliases.DocStringDefinitionBody

import scala.reflect.ClassTag

case class ScalaDocStringTypeDetails[T](contentType: String, body: DocStringDefinitionBody[T], tag: ClassTag[T])
