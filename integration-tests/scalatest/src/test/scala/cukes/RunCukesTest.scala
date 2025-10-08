package cukes

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("cukes"),
  features = Array("classpath:cukes"),
  plugin = Array("pretty")
)
class RunCukesTest extends CucumberSuite

