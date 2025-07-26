package io.cucumber.scala

import scala.reflect.ClassTag

case class ScalaParameterTypeDetails[R](
    name: String,
    regex: String,
    body: List[String] => R,
    tag: ClassTag[R],
    stackTraceElement: StackTraceElement
)
