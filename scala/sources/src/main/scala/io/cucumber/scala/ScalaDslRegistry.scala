package io.cucumber.scala

import io.cucumber.core.backend.{HookDefinition, StepDefinition}

import scala.collection.mutable.ArrayBuffer

class ScalaDslRegistry {

  val stepDefinitions = new ArrayBuffer[StepDefinition]

  val beforeHooks = new ArrayBuffer[HookDefinition]

  val beforeStepHooks = new ArrayBuffer[HookDefinition]

  val afterHooks = new ArrayBuffer[HookDefinition]

  val afterStepHooks = new ArrayBuffer[HookDefinition]

}

