package io.cucumber.scala

sealed trait HookType

sealed trait ScopedHookType extends HookType

object ScopedHookType {

  case object BEFORE extends ScopedHookType

  case object BEFORE_STEP extends ScopedHookType

  case object AFTER extends ScopedHookType

  case object AFTER_STEP extends ScopedHookType

}

sealed trait StaticHookType extends HookType

object StaticHookType {

  case object BEFORE_ALL extends StaticHookType

  case object AFTER_ALL extends StaticHookType

}
