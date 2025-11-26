package io.cucumber.scalatest

class SkippedThrowable(scenarioOrStep: NotificationLevel)
    extends Throwable(s"This ${scenarioOrStep.lowerCaseName} is skipped") {}

sealed abstract class NotificationLevel(val lowerCaseName: String)

object NotificationLevel {
  case object Scenario extends NotificationLevel("scenario")
  case object Step extends NotificationLevel("step")
}
