package io.cucumber.scala

import Aliases.{
  DefaultDataTableCellTransformerBody,
  DefaultDataTableEntryTransformerBody,
  DefaultParameterTransformerBody
}

case class ScalaDefaultParameterTransformerDetails(
    body: DefaultParameterTransformerBody,
    stackTraceElement: StackTraceElement
)

case class ScalaDefaultDataTableCellTransformerDetails(
    emptyPatterns: Seq[String],
    body: DefaultDataTableCellTransformerBody,
    stackTraceElement: StackTraceElement
)

case class ScalaDefaultDataTableEntryTransformerDetails(
    emptyPatterns: Seq[String],
    body: DefaultDataTableEntryTransformerBody,
    stackTraceElement: StackTraceElement
)
