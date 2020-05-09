package io.cucumber.scala

import io.cucumber.core.backend.{ParameterTypeDefinition, ScenarioScoped}
import io.cucumber.cucumberexpressions.{CaptureGroupTransformer, ParameterType}

import scala.jdk.CollectionConverters._

trait ScalaParameterTypeDefinition[R] extends ParameterTypeDefinition with AbstractGlueDefinition {

  val details: ScalaParameterTypeDetails[R]

  override val location: StackTraceElement = new Exception().getStackTrace()(3)

  private val transformer: CaptureGroupTransformer[R] = (parameterContent: Array[String]) => {
    details.body.apply(parameterContent.toList)
  }

  override val parameterType: ParameterType[R] = new ParameterType[R](details.name, Seq(details.regex).asJava, details.tag.runtimeClass.asInstanceOf[Class[R]], transformer)

}

object ScalaParameterTypeDefinition {

  def apply[R](stepDetails: ScalaParameterTypeDetails[R], scenarioScoped: Boolean): ScalaParameterTypeDefinition[R] = {
    if (scenarioScoped) {
      new ScalaScenarioScopedParameterTypeDefinition(stepDetails)
    } else {
      new ScalaGlobalParameterTypeDefinition(stepDetails)
    }
  }

}

class ScalaScenarioScopedParameterTypeDefinition[R](override val details: ScalaParameterTypeDetails[R]) extends ScalaParameterTypeDefinition[R] with ScenarioScoped {
}

class ScalaGlobalParameterTypeDefinition[R](override val details: ScalaParameterTypeDetails[R]) extends ScalaParameterTypeDefinition[R] {
}
