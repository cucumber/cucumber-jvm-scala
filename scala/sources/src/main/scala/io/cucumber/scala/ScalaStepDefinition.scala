package io.cucumber.scala

import java.lang.reflect.{InvocationTargetException, Type}
import java.util.{List => JList}

import io.cucumber.core.backend.{CucumberInvocationTargetException, ParameterInfo, StepDefinition}

import scala.collection.JavaConverters._
import scala.util.{Failure, Try}

/**
 * Implementation of step definition for scala.
 *
 * @param frame   Representation of a stack frame containing information about the context in which a
 *                step was defined. Allows retrospective queries about the definition of a step.
 * @param name    The name of the step definition class, e.g. cucumber.runtime.scala.test.CukesStepDefinitions
 * @param pattern The regex matcher that defines the cucumber step, e.g. /I eat (.*) cukes$/
 * @param types   Parameters types of body step definition
 * @param body    Function body of a step definition. This is what actually runs the code within the step def.
 */
class ScalaStepDefinition(frame: StackTraceElement,
                          name: String,
                          pattern: String,
                          types: Array[Type],
                          body: List[Any] => Any)
  extends AbstractGlueDefinition(frame)
    with StepDefinition {

  val parametersInfo: JList[ParameterInfo] = fromTypes(types)

  private def fromTypes(types: Array[Type]): JList[ParameterInfo] = {
    types
      .map(new ScalaTypeResolver(_))
      .map(new ScalaParameterInfo(_))
      .toList
      .asInstanceOf[List[ParameterInfo]]
      .asJava
  }

  override def execute(args: Array[AnyRef]): Unit = {
    Try {
      body(args.toList)
    }
      // We do this to respect the contract defined by StepDefinition
      // although throwing exceptions is not very Scala-ish
      .recoverWith {
        case ex => Failure(new CucumberInvocationTargetException(this, new InvocationTargetException(ex)))
      }
      .get

  }

  override def getPattern: String = pattern

  override def parameterInfos(): JList[ParameterInfo] = parametersInfo

  // Easier to just print out fileName and lineNumber
  override def getLocation(): String = frame.getFileName + ":" + frame.getLineNumber

}
