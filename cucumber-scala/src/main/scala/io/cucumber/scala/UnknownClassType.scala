package io.cucumber.scala

import io.cucumber.core.backend.CucumberBackendException

class UnknownClassType(clazz: Class[_], cause: Throwable)
    extends CucumberBackendException(
      s"Cucumber was not able to handle class ${clazz.getName}. Please report this issue to cucumber-scala project.",
      cause
    )
