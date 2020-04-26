package io.cucumber.scala

import scala.collection.mutable.ArrayBuffer

class ScalaDslRegistry {

  val stepDefinitions = new ArrayBuffer[ScalaStepDetails]

  val beforeHooks = new ArrayBuffer[ScalaHookDetails]

  val beforeStepHooks = new ArrayBuffer[ScalaHookDetails]

  val afterHooks = new ArrayBuffer[ScalaHookDetails]

  val afterStepHooks = new ArrayBuffer[ScalaHookDetails]

}

