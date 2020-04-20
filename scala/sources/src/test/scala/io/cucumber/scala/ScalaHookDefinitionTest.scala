package io.cucumber.scala

import org.junit.Assert.assertTrue
import org.junit.Test

class ScalaHookDefinitionTest {

  @Test
  def can_create_with_single_scenario_argument() = {
    var invoked = false
    val body = (scenario: Scenario) => {
      invoked = true
    }
    val definition = new ScalaHookDefinition("", 0, body)
    definition.execute(null)
    assertTrue(invoked)
  }

}
