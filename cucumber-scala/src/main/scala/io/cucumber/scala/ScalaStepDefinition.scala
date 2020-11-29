package io.cucumber.scala

import java.lang.reflect.{Type => JavaType}
import java.util.{List => JList}

import io.cucumber.core.backend.{ParameterInfo, ScenarioScoped, StepDefinition}

import scala.jdk.CollectionConverters._

trait ScalaStepDefinition extends StepDefinition with AbstractGlueDefinition {

  val stepDetails: ScalaStepDetails

  override val location: StackTraceElement = stepDetails.frame

  override val parameterInfos: JList[ParameterInfo] = fromTypes(
    stepDetails.types
  )

  private def fromTypes(types: Seq[JavaType]): JList[ParameterInfo] = {
    types
      .map(new ScalaTypeResolver(_))
      .map(new ScalaParameterInfo(_))
      .toList
      .asInstanceOf[List[ParameterInfo]]
      .asJava
  }

  override def execute(args: Array[AnyRef]): Unit = {
    executeAsCucumber {
      stepDetails.body(args.toList)
      ()
    }
  }

  override def getPattern: String = stepDetails.pattern

  // Easier to just print out fileName and lineNumber
  override def getLocation(): String =
    stepDetails.frame.getFileName + ":" + stepDetails.frame.getLineNumber

}

object ScalaStepDefinition {

  def apply(
      stepDetails: ScalaStepDetails,
      scenarioScoped: Boolean
  ): ScalaStepDefinition = {
    if (scenarioScoped) {
      new ScalaScenarioScopedStepDefinition(stepDetails)
    } else {
      new ScalaGlobalStepDefinition(stepDetails)
    }
  }

}

class ScalaScenarioScopedStepDefinition(
    override val stepDetails: ScalaStepDetails
) extends ScalaStepDefinition
    with ScenarioScoped {}

class ScalaGlobalStepDefinition(override val stepDetails: ScalaStepDetails)
    extends ScalaStepDefinition {}
