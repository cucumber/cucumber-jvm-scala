package glueclasses

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectPackages,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("glueclasses"))
@ConfigurationParameter(
  key = Constants.GLUE_CLASSES_PROPERTY_NAME,
  value = "glueclasses.SelectedSteps,glueclasses.SelectedObjectSteps$"
)
class RunGlueClassesTest
