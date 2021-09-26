package io.cucumber.scala.steps.errors.incorrectclasshooks

import io.cucumber.scala.ScalaDsl

//@formatter:off
class IncorrectClassHooksDefinition extends ScalaDsl {

  // On a single line to avoid difference between Scala versions for the location

  // A body that does not return Unit => interpreted as missing body
  BeforeAll { 22 }
  
  // A body that does not return Unit => interpreted as missing body
  Before { 1 }

  // A body that does not return Unit => interpreted as missing body
  BeforeStep { "toto" }

  // A body that does not return Unit => interpreted as missing body
  AfterAll { 66 }
  
  // A body that does not return Unit => interpreted as missing body
  After { 33 }

  // A body that does not return Unit => interpreted as missing body
  AfterStep { "toto" }
  
}
//@formatter:on
