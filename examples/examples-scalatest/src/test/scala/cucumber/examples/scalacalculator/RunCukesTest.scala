package cucumber.examples.scalacalculator

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

class RunCukesTest extends CucumberSuite {
  override val cucumberOptions = CucumberOptions(
    features = List("classpath:cucumber/examples/scalacalculator"),
    glue = List("cucumber.examples.scalacalculator"),
    plugin = List("pretty")
  )
}
