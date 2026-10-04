package io.cucumber.scalatest.fixtures.failing

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}
import org.scalatest.DoNotDiscover

/** Selects nothing */
@DoNotDiscover
class EmptyCucumberSuite extends CucumberSuite {
  override def cucumberOptions: CucumberOptions =
    CucumberOptions(tags = Some("@nothing"))
}
