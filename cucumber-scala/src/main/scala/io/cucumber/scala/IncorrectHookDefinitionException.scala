package io.cucumber.scala

import io.cucumber.core.backend.CucumberBackendException

object IncorrectHookDefinitionException {

  def errorMessage(expectedHooks: Seq[UndefinedHook]): String = {
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

}

class IncorrectHookDefinitionException(val undefinedHooks: Seq[UndefinedHook])
    extends CucumberBackendException(
      IncorrectHookDefinitionException.errorMessage(undefinedHooks)
    ) {}

case class UndefinedHook(
    hookType: HookType,
    stackTraceElement: StackTraceElement
)
