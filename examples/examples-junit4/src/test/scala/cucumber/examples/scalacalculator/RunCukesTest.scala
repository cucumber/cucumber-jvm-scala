package cucumber.examples.scalacalculator

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

import scala.annotation.nowarn

@nowarn
@RunWith(classOf[Cucumber])
@CucumberOptions(plugin = Array("pretty"))
class RunCukesTest
