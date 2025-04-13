package io.cucumber.scala

import io.cucumber.datatable.{DataTableType, TableEntryTransformer}

import java.util.{Map => JavaMap}
import scala.jdk.CollectionConverters._

trait ScalaDataTableEntryDefinition[T] extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableEntryTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableEntryTransformer[T] =
    (entry: JavaMap[String, String]) => {
      replaceEmptyPatternsWithEmptyString(entry.asScala.toMap)
        .map(details.body.transform)
        .get
    }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

class ScalaScenarioScopedDataTableEntryDefinition[T](
    override val details: ScalaDataTableEntryTypeDetails[T]
) extends ScalaDataTableEntryDefinition[T] {}

class ScalaGlobalDataTableEntryDefinition[T](
    override val details: ScalaDataTableEntryTypeDetails[T]
) extends ScalaDataTableEntryDefinition[T] {}
