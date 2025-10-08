package datatables

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite

@CucumberOptions(
  glue = Array("datatables"),
  features = Array("classpath:datatables"),
  plugin = Array("pretty")
)
class RunDatatablesTest extends CucumberSuite

