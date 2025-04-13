package io.cucumber.scala

import java.util.{List => JavaList}
import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableRowTransformer}

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

trait ScalaDataTableOptionalRowDefinition[T]
    extends ScalaDataTableTypeDefinition {

  val details: ScalaDataTableOptionalRowTypeDetails[T]

  override val emptyPatterns: Seq[String] = details.emptyPatterns

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: TableRowTransformer[T] = (row: JavaList[String]) => {
    details.body.transform(
      row.asScala
        .map(replaceEmptyPatternsWithEmptyString)
        .map(Option.apply)
        .toSeq
    )
  }

  override val dataTableType =
    new DataTableType(details.tag.runtimeClass, transformer)

}

@nowarn
class ScalaScenarioScopedDataTableOptionalRowDefinition[T](
    override val details: ScalaDataTableOptionalRowTypeDetails[T]
) extends ScalaDataTableOptionalRowDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDataTableOptionalRowDefinition[T](
    override val details: ScalaDataTableOptionalRowTypeDetails[T]
) extends ScalaDataTableOptionalRowDefinition[T] {}
