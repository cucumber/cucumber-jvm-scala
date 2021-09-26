package cucumber.examples.scalacalculator

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  // Note: these are the default values (can be ommited)
  glue = Array("cucumber.examples.scalacalculator"),
  features = Array("classpath:cucumber/examples/scalacalculator")
)
class RunCukesTest
