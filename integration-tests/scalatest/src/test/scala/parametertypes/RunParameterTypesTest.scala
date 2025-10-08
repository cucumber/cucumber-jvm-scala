package parametertypes

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("parametertypes"),
  features = Array("classpath:parametertypes"),
  plugin = Array("pretty")
)
class RunParameterTypesTest extends CucumberSuite

