package io.cucumber.scala

import io.cucumber.core.backend.Located

abstract class AbstractGlueDefinition(location: StackTraceElement) extends Located {

  def getLocation(): String = {
    location.toString
  }

  def isDefinedAt(stackTraceElement: StackTraceElement): Boolean = {
    location.getFileName != null && location.getFileName == stackTraceElement.getFileName
  }

}
