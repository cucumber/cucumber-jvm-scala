package cucumber.examples.scalacalculator

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}

class RunCukesTest extends CucumberSuite {
  override val cucumberOptions = CucumberOptions(
    features = List("classpath:cucumber/examples/scalacalculator"),
    glue = List("cucumber.examples.scalacalculator"),
    plugin = List("pretty")
    // Example with tags filter (commented out):
    // tags = Some("@foo or @bar")  // Run scenarios tagged with @foo or @bar
    // tags = Some("not @wip")      // Skip scenarios tagged with @wip
  )
}
