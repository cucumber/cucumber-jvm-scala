package docstringdatatable

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectPackages,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("docstringdatatable"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "docstringdatatable"
)
class RunDocStringAndDataTableTest
