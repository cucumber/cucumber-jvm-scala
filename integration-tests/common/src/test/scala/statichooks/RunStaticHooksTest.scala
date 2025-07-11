package statichooks

import io.cucumber.junit.platform.engine.Constants
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.{AfterAll, BeforeAll}
import org.junit.platform.suite.api.{
  ConfigurationParameter,
  IncludeEngines,
  SelectPackages,
  Suite
}

@Suite
@IncludeEngines(Array("cucumber"))
@SelectPackages(Array("statichooks"))
@ConfigurationParameter(
  key = Constants.GLUE_PROPERTY_NAME,
  value = "statichooks"
)
class RunStaticHooksTest

object RunStaticHooksTest {

  @BeforeAll
  def beforeAllJunit(): Unit = {
    assertEquals(
      0L,
      StaticHooksSteps.countBeforeAll.toLong,
      "Before Cucumber's BeforeAll"
    )
  }

  @AfterAll
  def afterAllJunit(): Unit = {
    assertEquals(
      1L,
      StaticHooksSteps.countAfterAll.toLong,
      "After Cucumber's AfterAll"
    )
  }

}
