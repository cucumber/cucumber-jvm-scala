package io.cucumber.scala

import io.cucumber.core.exception.CucumberException

object IncorrectStepDefinitionException {

  // Allows to use """ in """xxx"""" strings
  private val tripleDoubleQuotes = "\"\"\""

  val errorMessage: String =
    s"""The arguments received doesn't match the step definition.
      |This can happen if you are using a regular expression in your step definition with optional capture groups but mandatory parameters.
      |
      |For instance:
      |
      |  Given($tripleDoubleQuotes^I am logged in(?: as (.+))?$$$tripleDoubleQuotes) { (user: String) =>
      |    // Some code
      |  }
      |
      |For now, the easiest solution is to declare two steps: one with the capture groups, one without.
      |If you feel this is not working for you, please manifest yourself on https://github.com/cucumber/cucumber-jvm-scala/issues/3""".stripMargin

}

class IncorrectStepDefinitionException
    extends CucumberException(IncorrectStepDefinitionException.errorMessage) {}
