package misc

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("misc"),
  features = Array("classpath:misc"),
  plugin = Array("pretty")
)
class RunMiscTest extends CucumberSuite

