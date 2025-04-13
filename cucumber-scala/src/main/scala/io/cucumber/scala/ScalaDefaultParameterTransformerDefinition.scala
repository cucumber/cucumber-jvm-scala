package io.cucumber.scala

import io.cucumber.core.backend.DefaultParameterTransformerDefinition
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer

import java.lang.reflect.Type

trait ScalaDefaultParameterTransformerDefinition
    extends DefaultParameterTransformerDefinition
    with AbstractGlueDefinition {

  val details: ScalaDefaultParameterTransformerDetails

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

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

class ScalaScenarioScopedDefaultParameterTransformerDefinition(
    override val details: ScalaDefaultParameterTransformerDetails
) extends ScalaDefaultParameterTransformerDefinition {}

class ScalaGlobalDefaultParameterTransformerDefinition(
    override val details: ScalaDefaultParameterTransformerDetails
) extends ScalaDefaultParameterTransformerDefinition {}
