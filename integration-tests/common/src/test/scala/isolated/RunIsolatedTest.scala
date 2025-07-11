package isolated

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectPackages,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("isolated"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "isolated"
)
class RunIsolatedTest
