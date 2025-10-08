package isolated

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("isolated"),
  features = Array("classpath:isolated"),
  plugin = Array("pretty")
)
class RunIsolatedTest extends CucumberSuite

