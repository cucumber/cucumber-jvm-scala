package io.cucumber.scala

import io.cucumber.core.backend.CucumberBackendException

sealed abstract class IncorrectHookDefinitionException(message: String)
    extends CucumberBackendException(message)

object IncorrectHookDefinitionException {

  def undefinedHooksErrorMessage(expectedHooks: Seq[UndefinedHook]): String = {
    val hooksListToDisplay = expectedHooks.map { eh =>
      s" - ${eh.stackTraceElement.getFileName}:${eh.stackTraceElement.getLineNumber} (${eh.hookType})"
    }

    s"""Some hooks are not defined properly:
       |${hooksListToDisplay.mkString("\n")}
       |
       |This can be caused by defining hooks where the body returns a Int or String rather than Unit.
       |
       |For instance, the following code:
       |
       |  Before {
       |    someInitMethodReturningInt()
       |  }
       |
       |Should be replaced with:
       |
       |  Before {
       |    someInitMethodReturningInt()
       |    ()
       |  }
       |""".stripMargin
  }

  def scenarioScopedStaticHookErrorMessage(
      staticHooks: Seq[ScalaStaticHookDetails]
  ): String = {
    val hooksListToDisplay: Seq[String] = staticHooks.map { h =>
      s" - ${h.stackTraceElement.getFileName}:${h.stackTraceElement.getLineNumber}"
    }

    s"""Some hooks are not defined properly:
       |${hooksListToDisplay.mkString("\n")}
       |
       |This can be caused by defining static hooks (BeforeAll/AfterAll) in a class rather than in a object.
       |Such hooks can only be defined in a static context.
       |""".stripMargin
  }

}

class UndefinedHooksException(val undefinedHooks: Seq[UndefinedHook])
    extends IncorrectHookDefinitionException(
      IncorrectHookDefinitionException.undefinedHooksErrorMessage(
        undefinedHooks
      )
    ) {}

class ScenarioScopedStaticHookException(
    val staticHooks: Seq[ScalaStaticHookDetails]
) extends IncorrectHookDefinitionException(
      IncorrectHookDefinitionException.scenarioScopedStaticHookErrorMessage(
        staticHooks
      )
    ) {}

case class UndefinedHook(
    hookType: HookType,
    stackTraceElement: StackTraceElement
)
