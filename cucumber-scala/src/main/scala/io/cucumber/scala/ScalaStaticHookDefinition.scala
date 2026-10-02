package io.cucumber.scala

import io.cucumber.core.backend.StaticHookDefinition

import java.util.Optional

trait ScalaStaticHookDefinition
    extends StaticHookDefinition
    with AbstractGlueDefinition {

  val hookDetails: ScalaStaticHookDetails

  override val location: StackTraceElement = hookDetails.stackTraceElement

  override def execute(): Unit = {
    executeAsCucumber(hookDetails.body.apply())
  }

  override def getOrder: Int = hookDetails.order

  override def getHookType: Optional[StaticHookDefinition.HookType] = {
    val javaHookType = hookDetails.hookType match {
      case StaticHookType.BEFORE_ALL => StaticHookDefinition.HookType.BEFORE_ALL
      case StaticHookType.AFTER_ALL  => StaticHookDefinition.HookType.AFTER_ALL
    }
    Optional.of(javaHookType)
  }

}

object ScalaStaticHookDefinition {

  def apply(
      scalaHookDetails: ScalaStaticHookDetails
  ): ScalaStaticHookDefinition = {
    new ScalaGlobalStaticHookDefinition(scalaHookDetails)
  }

}

class ScalaGlobalStaticHookDefinition(
    override val hookDetails: ScalaStaticHookDetails
) extends ScalaStaticHookDefinition {}
