package io.cucumber.scala.steps.errors.staticclasshooks

import io.cucumber.scala.ScalaDsl

//@formatter:off
class StaticClassHooksDefinition extends ScalaDsl {

  // On a single line to avoid difference between Scala versions for the location

  // Static hook not allowed in classes
  BeforeAll { () }
  
  // Static hook not allowed in classes
  AfterAll { () }
  
}
//@formatter:on
