package docstring

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("docstring"),
  features = Array("classpath:docstring"),
  plugin = Array("pretty")
)
class RunDocStringTest extends CucumberSuite

