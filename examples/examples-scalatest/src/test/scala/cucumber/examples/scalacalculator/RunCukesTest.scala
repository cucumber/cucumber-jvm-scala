package cucumber.examples.scalacalculator

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

class RunCukesTest extends CucumberSuite {
  override val cucumberOptions = CucumberOptions(
    plugin = List("pretty")
  )
}
