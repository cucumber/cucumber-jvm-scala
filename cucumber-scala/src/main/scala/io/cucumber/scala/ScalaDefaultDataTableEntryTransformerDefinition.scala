package io.cucumber.scala

import java.lang.reflect.Type
import java.util.{Map => JavaMap}
import io.cucumber.core.backend.{
  DefaultDataTableEntryTransformerDefinition,
  ScenarioScoped
}
import io.cucumber.datatable.{
  TableCellByTypeTransformer,
  TableEntryByTypeTransformer
}

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

trait ScalaDefaultDataTableEntryTransformerDefinition
    extends DefaultDataTableEntryTransformerDefinition
    with AbstractDatatableElementTransformerDefinition {

  val details: ScalaDefaultDataTableEntryTransformerDetails

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  override val tableEntryByTypeTransformer: TableEntryByTypeTransformer = (
      fromValue: JavaMap[String, String],
      toValueType: Type,
      _: TableCellByTypeTransformer
  ) => {
    replaceEmptyPatternsWithEmptyString(fromValue.asScala.toMap)
      .map(details.body.apply(_, toValueType))
      .get
  }

  override val headersToProperties: Boolean = true

}

object ScalaDefaultDataTableEntryTransformerDefinition {

  def apply(
      details: ScalaDefaultDataTableEntryTransformerDetails,
      scenarioScoped: Boolean
  ): ScalaDefaultDataTableEntryTransformerDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedDataTableEntryTransformerDefinition(details)
    } else {
      new ScalaGlobalDataTableEntryTransformerDefinition(details)
    }
  }

}

@nowarn
class ScalaScenarioScopedDataTableEntryTransformerDefinition(
    override val details: ScalaDefaultDataTableEntryTransformerDetails
) extends ScalaDefaultDataTableEntryTransformerDefinition
    with ScenarioScoped {}

class ScalaGlobalDataTableEntryTransformerDefinition(
    override val details: ScalaDefaultDataTableEntryTransformerDetails
) extends ScalaDefaultDataTableEntryTransformerDefinition {}
