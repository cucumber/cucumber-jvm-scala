package io.cucumber.scala

sealed trait HookType

object HookType {

  case object BEFORE extends HookType

  case object BEFORE_STEP extends HookType

  case object AFTER extends HookType

  case object AFTER_STEP extends HookType

}
