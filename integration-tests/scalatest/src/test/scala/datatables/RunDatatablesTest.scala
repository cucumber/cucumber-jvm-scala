package datatables

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

@CucumberOptions(
  glue = Array("datatables"),
  features = Array("classpath:datatables"),
  plugin = Array("pretty")
)
class RunDatatablesTest extends CucumberSuite
