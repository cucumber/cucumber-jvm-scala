package io.cucumber.scala

import java.util.{List => JavaList}
import io.cucumber.core.backend.ScenarioScoped
import io.cucumber.datatable.{DataTableType, TableRowTransformer}

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

trait ScalaDataTableOptionalRowDefinition[T]
    extends ScalaDataTableTypeDefinition {

  override val details: ScalaDataTableOptionalRowTypeDetails[T]

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
