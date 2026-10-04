package cucumber.examples.scalacalculator

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

class RunCukesTest extends CucumberSuite {
  override def cucumberOptions: CucumberOptions =
    CucumberOptions(plugin = Seq("pretty"))
}
