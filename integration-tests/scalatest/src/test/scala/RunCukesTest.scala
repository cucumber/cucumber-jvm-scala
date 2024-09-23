package cukes

import io.cucumber.scalatest.{CucumberSuite, CucumberSuiteOptions}

class RunCukesTest extends CucumberSuite with CucumberSuiteOptions {

  override def featuresPath: Seq[String] = Nil

  override def gluePackages: Seq[String] = Nil
}
