package cucumber.examples.scalacalculator

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api._

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("cucumber.examples.scalacalculator"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "cucumber.examples.scalacalculator"
)
class RunCukesTest
