package jackson

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectPackages,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("jackson"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "jackson"
)
class RunJacksonTest
