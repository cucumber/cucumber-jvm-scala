package io.cucumber.scala

/**
 * Contains some aliases to help match this codebase with cucumber-java
 */
object Aliases {

  type HookBody = Scenario => Unit

  type StepDefinitionBody = () => Unit

}
