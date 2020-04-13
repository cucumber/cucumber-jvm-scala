package io.cucumber.scala

import java.net.URI
import java.util

import io.cucumber.core.backend.{Status, TestCaseState}

class Scenario(val delegate: TestCaseState) {

  def getSourceTagNames: util.Collection[String] = delegate.getSourceTagNames

  def getStatus: Status = Status.valueOf(delegate.getStatus.name)

  def isFailed: Boolean = delegate.isFailed

  @deprecated
  def embed(data: Array[Byte], mediaType: String): Unit = {
    delegate.embed(data, mediaType)
  }

  def embed(data: Array[Byte], mediaType: String, name: String): Unit = {
    delegate.embed(data, mediaType, name)
  }

  def write(text: String): Unit = {
    delegate.write(text)
  }

  def getName: String = delegate.getName

  def getId: String = delegate.getId

  def getUri: URI = delegate.getUri

  def getLine: Integer = delegate.getLine

}
