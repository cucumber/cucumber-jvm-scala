package misc

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}


@CucumberOptions(
  glue = Array("misc"),
  features = Array("classpath:misc"),
  plugin = Array("pretty")
)
class RunMiscTest extends CucumberSuite

