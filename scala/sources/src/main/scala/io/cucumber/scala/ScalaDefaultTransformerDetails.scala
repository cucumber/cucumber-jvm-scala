package io.cucumber.scala

import io.cucumber.scala.Aliases.{DefaultDataTableCellTransformerBody, DefaultDataTableEntryTransformerBody, DefaultParameterTransformerBody}

case class ScalaDefaultParameterTransformerDetails(body: DefaultParameterTransformerBody)

case class ScalaDefaultDataTableCellTransformerDetails(emptyPatterns: Seq[String], body: DefaultDataTableCellTransformerBody)

case class ScalaDefaultDataTableEntryTransformerDetails(emptyPatterns: Seq[String], body: DefaultDataTableEntryTransformerBody)
