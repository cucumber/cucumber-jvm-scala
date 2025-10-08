package parametertypes

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}


@CucumberOptions(
  glue = Array("parametertypes"),
  features = Array("classpath:parametertypes"),
  plugin = Array("pretty")
)
class RunParameterTypesTest extends CucumberSuite

