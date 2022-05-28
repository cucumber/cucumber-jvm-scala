package statichooks

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.{AfterClass, BeforeClass}
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions()
class RunStaticHooksTest

object RunStaticHooksTest {

  @BeforeClass
  def beforeAllJunit(): Unit = {
    assertEquals(
      "Before Cucumber's BeforeAll",
      0L,
      StaticHooksSteps.countBeforeAll.toLong
    )
  }

  @AfterClass
  def afterAllJunit(): Unit = {
    assertEquals(
      "After Cucumber's AfterAll",
      1L,
      StaticHooksSteps.countAfterAll.toLong
    )
  }

}
