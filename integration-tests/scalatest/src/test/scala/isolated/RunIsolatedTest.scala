package isolated

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

@CucumberOptions(
  glue = Array("isolated"),
  features = Array("classpath:isolated"),
  plugin = Array("pretty")
)
class RunIsolatedTest extends CucumberSuite
