package cukes

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api._

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("cukes"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "cukes"
)
class RunCukesTest
