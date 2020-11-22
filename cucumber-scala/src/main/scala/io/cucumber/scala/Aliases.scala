package io.cucumber.scala

/** Contains some aliases to help match this codebase with cucumber-java
  */
object Aliases {

  type HookDefinitionBody = Scenario => Unit

  type StepDefinitionBody = () => Unit

  type DocStringDefinitionBody[T] = String => T

  type DefaultParameterTransformerBody =
    (String, java.lang.reflect.Type) => AnyRef

  type DefaultDataTableCellTransformerBody =
    (String, java.lang.reflect.Type) => AnyRef

  type DefaultDataTableEntryTransformerBody =
    (Map[String, String], java.lang.reflect.Type) => AnyRef

}
