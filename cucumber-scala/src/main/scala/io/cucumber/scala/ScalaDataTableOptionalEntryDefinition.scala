package io.cucumber.scala

import io.cucumber.datatable.{DataTableType, TableEntryTransformer}

import java.util.{Map => JavaMap}
import scala.jdk.CollectionConverters._

trait ScalaDataTableOptionalEntryDefinition[T]
    extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableOptionalEntryTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableEntryTransformer[T] =
    (entry: JavaMap[String, String]) => {
      replaceEmptyPatternsWithEmptyString(entry.asScala.toMap)
        .map(_.map { case (k, v) => (k, Option(v)) })
        .map(details.body.transform)
        .get
    }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

class ScalaScenarioScopedDataTableOptionalEntryDefinition[T](
    override val details: ScalaDataTableOptionalEntryTypeDetails[T]
) extends ScalaDataTableOptionalEntryDefinition[T] {}

class ScalaGlobalDataTableOptionalEntryDefinition[T](
    override val details: ScalaDataTableOptionalEntryTypeDetails[T]
) extends ScalaDataTableOptionalEntryDefinition[T] {}
