package io.cucumber.scala.steps.errors.incorrectobjecthooks

import io.cucumber.scala.ScalaDsl

//@formatter:off
object IncorrectObjectHooksDefinition extends ScalaDsl {

  // On a single line to avoid difference between Scala versions for the location

  // A body that does not return Unit => interpreted as missing body
  Before { 1 }

  // A body that does not return Unit => interpreted as missing body
  BeforeStep { "toto" }

  // A body that does not return Unit => interpreted as missing body
  After { 33 }

  // A body that does not return Unit => interpreted as missing body
  AfterStep { "toto" }

}
//@formatter:on