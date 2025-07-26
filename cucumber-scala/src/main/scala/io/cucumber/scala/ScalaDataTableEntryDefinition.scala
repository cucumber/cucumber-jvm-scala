package io.cucumber.scala

import java.util.{Map => JavaMap}
import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableEntryTransformer}

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

trait ScalaDataTableEntryDefinition[T] extends ScalaDataTableTypeDefinition {

  override val details: ScalaDataTableEntryTypeDetails[T]

  private val transformer: TableEntryTransformer[T] =
    (entry: JavaMap[String, String]) => {
      replaceEmptyPatternsWithEmptyString(entry.asScala.toMap)
        .map(details.body.transform)
        .get
    }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

@nowarn
class ScalaScenarioScopedDataTableEntryDefinition[T](
    override val details: ScalaDataTableEntryTypeDetails[T]
) extends ScalaDataTableEntryDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDataTableEntryDefinition[T](
    override val details: ScalaDataTableEntryTypeDetails[T]
) extends ScalaDataTableEntryDefinition[T] {}
