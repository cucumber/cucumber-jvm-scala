package io.cucumber.scala

import io.cucumber.core.backend.{DocStringTypeDefinition, ScenarioScoped}
import io.cucumber.docstring.DocStringType
import io.cucumber.docstring.DocStringType.Transformer

import scala.annotation.nowarn

abstract class ScalaDocStringTypeDefinition[T]
    extends DocStringTypeDefinition
    with AbstractGlueDefinition {

  val details: ScalaDocStringTypeDetails[T]

  override val location: StackTraceElement = details.stackTraceElement

  private val transformer: Transformer[T] = (s: String) => {
    details.body.apply(s)
  }

  override val docStringType: DocStringType =
    new DocStringType(details.`type`, details.contentType, transformer)

}

object ScalaDocStringTypeDefinition {

  def apply[T](
      details: ScalaDocStringTypeDetails[T],
      scenarioScoped: Boolean
  ): ScalaDocStringTypeDefinition[T] = {
    if (scenarioScoped) {
      new ScalaScenarioScopedDocStringTypeDefinition(details)
    } else {
      new ScalaGlobalDocStringTypeDefinition(details)
    }
  }

}

@nowarn
class ScalaScenarioScopedDocStringTypeDefinition[T](
    override val details: ScalaDocStringTypeDetails[T]
) extends ScalaDocStringTypeDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDocStringTypeDefinition[T](
    override val details: ScalaDocStringTypeDetails[T]
) extends ScalaDocStringTypeDefinition[T] {}
