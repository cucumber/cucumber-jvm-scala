package io.cucumber.scala

import java.lang.reflect.{Type => JType}

/** Implementation of step definition for scala.
  *
  * @param frame
  *   Representation of a stack frame containing information about the context
  *   in which a step was defined. Allows retrospective queries about the
  *   definition of a step.
  * @param name
  *   The name of the step definition class, e.g.
  *   cucumber.runtime.scala.test.CukesStepDefinitions
  * @param pattern
  *   The regex matcher that defines the cucumber step, e.g. /I eat (.*) cukes$/
  * @param types
  *   Parameters types of body step definition
  * @param body
  *   Function body of a step definition. This is what actually runs the code
  *   within the step def.
  */
private[scala] case class ScalaStepDetails(
    frame: StackTraceElement,
    name: String,
    pattern: String,
    types: Seq[JType],
    body: List[Any] => Any
)
