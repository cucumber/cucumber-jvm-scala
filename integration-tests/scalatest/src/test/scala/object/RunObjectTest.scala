package `object`

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("object"),
  features = Array("classpath:object"),
  plugin = Array("pretty")
)
class RunObjectTest extends CucumberSuite

