package cucumber.examples.scalacalculator

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

@CucumberOptions(
  features = Array("classpath:cucumber/examples/scalacalculator"),
  glue = Array("cucumber.examples.scalacalculator"),
  plugin = Array("pretty")
)
class RunCukesTest extends CucumberSuite
