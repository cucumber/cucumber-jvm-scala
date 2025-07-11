package `object`

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectPackages,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("object"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "object"
)
class RunObjectTest
