package statichooks

import io.cucumber.core.options.CucumberOptions
import io.cucumber.scalatest.CucumberSuite
import org.scalatest.{BeforeAndAfterAll, Assertions}

@CucumberOptions(
  glue = Array("statichooks"),
  features = Array("classpath:statichooks"),
  plugin = Array("pretty")
)
class RunStaticHooksTest extends CucumberSuite with BeforeAndAfterAll with Assertions {

  override def beforeAll(): Unit = {
    super.beforeAll()
    assert(
      StaticHooksSteps.countBeforeAll.toLong == 0L,
      "Before Cucumber's BeforeAll"
    )
  }

  override def afterAll(): Unit = {
    assert(
      StaticHooksSteps.countAfterAll.toLong == 1L,
      "After Cucumber's AfterAll"
    )
    super.afterAll()
  }

}

