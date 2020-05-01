package io.cucumber.scala

import java.lang.reflect.InvocationTargetException

import io.cucumber.core.backend.{CucumberInvocationTargetException, Located}

import scala.util.{Failure, Try}

trait AbstractGlueDefinition extends Located {

  val location: StackTraceElement

  override def getLocation(): String = {
    location.toString
  }

  override def isDefinedAt(stackTraceElement: StackTraceElement): Boolean = {
    location.getFileName != null && location.getFileName == stackTraceElement.getFileName
  }

  /**
   * Executes the block of code and handle failures in the way asked by Cucumber specification: that is throwing a CucumberInvocationTargetException.
   */
  protected def executeAsCucumber(block: => Unit): Unit = {
    Try(block)
      .recoverWith {
        case ex => Failure(new CucumberInvocationTargetException(this, new InvocationTargetException(ex)))
      }
      .get
  }

}
