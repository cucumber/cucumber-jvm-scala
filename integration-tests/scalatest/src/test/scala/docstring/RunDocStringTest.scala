package docstring

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

@CucumberOptions(
  glue = Array("docstring"),
  features = Array("classpath:docstring"),
  plugin = Array("pretty")
)
class RunDocStringTest extends CucumberSuite
