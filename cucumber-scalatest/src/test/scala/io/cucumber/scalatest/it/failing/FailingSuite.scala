package io.cucumber.scalatest.it.failing

import io.cucumber.scalatest.{CucumberOptions, CucumberSuite}
import org.scalatest.DoNotDiscover

@DoNotDiscover
class FailingSuite extends CucumberSuite {

  override val cucumberOptions: CucumberOptions = CucumberOptions(plugin = List("pretty"))

}
