package io.cucumber.scala

import io.cucumber.core.backend.StaticHookDefinition

trait ScalaStaticHookDefinition
    extends StaticHookDefinition
    with AbstractGlueDefinition {

  val hookDetails: ScalaStaticHookDetails

  override val location: StackTraceElement = hookDetails.stackTraceElement

  override def execute(): Unit = {
    executeAsCucumber(hookDetails.body.apply())
  }

  override def getOrder: Int = hookDetails.order

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
