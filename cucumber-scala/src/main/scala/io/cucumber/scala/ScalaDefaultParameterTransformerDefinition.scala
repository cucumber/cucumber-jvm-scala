package io.cucumber.scala

import java.lang.reflect.Type
import io.cucumber.core.backend.{
  DefaultParameterTransformerDefinition,
  ScenarioScoped
}
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer

import scala.annotation.nowarn

trait ScalaDefaultParameterTransformerDefinition
    extends DefaultParameterTransformerDefinition
    with AbstractGlueDefinition {

  val details: ScalaDefaultParameterTransformerDetails

  override val location: StackTraceElement = details.stackTraceElement

  override val parameterByTypeTransformer: ParameterByTypeTransformer =
    (fromValue: String, toValue: Type) => {
      details.body.apply(fromValue, toValue)
    }

}

object ScalaDefaultParameterTransformerDefinition {

  def apply(
      details: ScalaDefaultParameterTransformerDetails,
      scenarioScoped: Boolean
  ): ScalaDefaultParameterTransformerDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedDefaultParameterTransformerDefinition(details)
    } else {
      new ScalaGlobalDefaultParameterTransformerDefinition(details)
    }
  }

}

@nowarn
class ScalaScenarioScopedDefaultParameterTransformerDefinition(
    override val details: ScalaDefaultParameterTransformerDetails
) extends ScalaDefaultParameterTransformerDefinition
    with ScenarioScoped {}

class ScalaGlobalDefaultParameterTransformerDefinition(
    override val details: ScalaDefaultParameterTransformerDetails
) extends ScalaDefaultParameterTransformerDefinition {}
