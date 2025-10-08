package cukes

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

@CucumberOptions(
  plugin = Array("pretty")
)
class RunCukesTest extends CucumberSuite

