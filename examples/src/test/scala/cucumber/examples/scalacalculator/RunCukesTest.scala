package cucumber.examples.scalacalculator

import cucumber.api.CucumberOptions
import org.junit.runner.RunWith
import cucumber.api.junit.Cucumber

@RunWith(classOf[Cucumber])
@CucumberOptions(glue = Array("src/test/scala/cucumber/examples/scalacalculator"))
class RunCukesTest