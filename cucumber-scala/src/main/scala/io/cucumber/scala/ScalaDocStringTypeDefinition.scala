package io.cucumber.scala

import io.cucumber.core.backend.{DocStringTypeDefinition, ScenarioScoped}
import io.cucumber.docstring.DocStringType
import io.cucumber.docstring.DocStringType.Transformer

import scala.reflect.ClassTag

trait ScalaDocStringTypeDefinition[T]
    extends DocStringTypeDefinition
    with AbstractGlueDefinition {

  val details: ScalaDocStringTypeDetails[T]

  implicit val ev: ClassTag[T]

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: Transformer[T] = (s: String) => {
    details.body.apply(s)
  }

  override val docStringType: DocStringType =
    new DocStringType(ev.runtimeClass, details.contentType, transformer)

}

object ScalaDocStringTypeDefinition {

  def apply[T](
      details: ScalaDocStringTypeDetails[T],
      scenarioScoped: Boolean
  ): ScalaDocStringTypeDefinition[T] = {
    if (scenarioScoped) {
      new ScalaScenarioScopedDocStringTypeDefinition(details)(details.tag)
    } else {
      new ScalaGlobalDocStringTypeDefinition(details)(details.tag)
    }
  }

}

class ScalaScenarioScopedDocStringTypeDefinition[T](
    override val details: ScalaDocStringTypeDetails[T]
)(implicit val ev: ClassTag[T])
    extends ScalaDocStringTypeDefinition[T]
    with ScenarioScoped {}

class ScalaGlobalDocStringTypeDefinition[T](
    override val details: ScalaDocStringTypeDetails[T]
)(implicit val ev: ClassTag[T])
    extends ScalaDocStringTypeDefinition[T] {}
