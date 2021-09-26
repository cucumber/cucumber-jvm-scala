package cucumber.examples.scalacalculator

import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectClasspathResource,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectClasspathResource(
  "cucumber/examples/scalacalculator"
) // Location of *.features file
@ConfigurationParameter( // Location of glue code
  key = GLUE_PROPERTY_NAME,
  value = "cucumber.examples.scalacalculator"
)
class RunCukesTest
