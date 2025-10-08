package `object`

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}


@CucumberOptions(
  glue = Array("object"),
  features = Array("classpath:object"),
  plugin = Array("pretty")
)
class RunObjectTest extends CucumberSuite

