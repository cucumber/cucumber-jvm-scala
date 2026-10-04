package io.cucumber.scalatest.fixtures.failing

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}
import org.scalatest.DoNotDiscover

/** Only selects the scenario that passes */
@DoNotDiscover
class FilteredCucumberSuite extends CucumberSuite {
  override def cucumberOptions: CucumberOptions =
    CucumberOptions(tags = Some("@passing"))
}
