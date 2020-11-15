package io.cucumber.scala

import java.lang.reflect.InvocationTargetException
import java.util.Optional

import io.cucumber.core.backend.{CucumberInvocationTargetException, Located, SourceReference}

import scala.util.{Failure, Try}

trait AbstractGlueDefinition extends Located {

  val location: StackTraceElement

  lazy val sourceReference: SourceReference = SourceReference.fromStackTraceElement(location)

  override def getLocation(): String = {
    location.toString
  }

  override def isDefinedAt(stackTraceElement: StackTraceElement): Boolean = {
    location.getFileName != null && location.getFileName == stackTraceElement.getFileName
  }

  override def getSourceReference(): Optional[SourceReference] = {
    Optional.of(sourceReference)
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
