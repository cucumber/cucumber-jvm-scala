package io.cucumber.scala

import java.net.URI
import java.util

import io.cucumber.core.backend.{Status, TestCaseState}

/**
 * Before or After Hooks that declare a parameter of this type will receive an instance of this class.
 * It allows writing text and embedding media into reports, as well as inspecting results (in an After block).
 * <p>
 * Note: This class is not intended to be used to create reports. To create custom reports use
 * the `io.cucumber.plugin.Plugin` class. The plugin system provides a much richer access to Cucumbers then
 * hooks after could provide. For an example see `io.cucumber.core.plugin.PrettyFormatter`.
 */
class Scenario(val delegate: TestCaseState) {

  def getSourceTagNames: util.Collection[String] = delegate.getSourceTagNames

  /**
   * Returns the current status of this scenario.
   * <p>
   * The scenario status is calculate as the most severe status of the
   * executed steps in the scenario so far.
   *
   * @return the current status of this scenario
   */
  def getStatus: Status = Status.valueOf(delegate.getStatus.name)

  /**
   * @return true if and only if { @link #getStatus()} returns "failed"
   */
  def isFailed: Boolean = delegate.isFailed

  @deprecated(message = "Use attach instead", since = "5.x")
  def embed(data: Array[Byte], mediaType: String): Unit = {
    delegate.embed(data, mediaType)
  }

  @deprecated(message = "Use attach instead", since = "5.7.0")
  def embed(data: Array[Byte], mediaType: String, name: String): Unit = {
    attach(data, mediaType, name)
  }

  /**
   * Attach data to the report(s).
   * <pre>
   * {@code
   * // Attach a screenshot. See your UI automation tool's docs for
     * // details about how to take a screenshot.
     * scenario.attach(pngBytes, "image/png", "Bartholomew and the Bytes of the Oobleck");
     * }
   * </pre>
   * <p>
   * To ensure reporting tools can understand what the data is a
   * {@code mediaType} must be provided. For example: {@code text/plain},
   * {@code image/png}, {@code text/html;charset=utf-8}.
   * <p>
   * Media types are defined in <a href= https://tools.ietf.org/html/rfc7231#section-3.1.1.1>RFC 7231 Section 3.1.1.1</a>.
   *
   * @param data      what to attach, for example an image.
   * @param mediaType what is the data?
   * @param name      attachment name
   */
  def attach(data: Array[Byte], mediaType: String, name: String): Unit = {
    delegate.attach(data, mediaType, name)
  }

  @deprecated(message = "Use log instead", since = "5.7.0")
  def write(text: String): Unit = {
    log(text)
  }

  /**
   * Outputs some text into the report.
   *
   * @param text what to put in the report.
   */
  def log(text: String): Unit = {
    delegate.log(text)
  }

  /**
   * @return the name of the Scenario
   */
  def getName: String = delegate.getName

  /**
   * @return the id of the Scenario.
   */
  def getId: String = delegate.getId

  /**
   * @return the uri of the Scenario.
   */
  def getUri: URI = delegate.getUri

  /**
   * @return the line in the feature file of the Scenario. If this is a Scenario
   *         from Scenario Outlines this will return the line of the example row in
   *         the Scenario Outline.
   */
  def getLine: Integer = delegate.getLine

}
