package io.cucumber.scala

import java.net.URI
import java.util

import io.cucumber.core.backend.{Status, TestCaseState}

/** Before or After Hooks that declare a parameter of this type will receive an instance of this class.
  * It allows writing text and embedding media into reports, as well as inspecting results (in an After block).
  * <p>
  * Note: This class is not intended to be used to create reports. To create custom reports use
  * the `io.cucumber.plugin.Plugin` class. The plugin system provides a much richer access to Cucumbers then
  * hooks after could provide. For an example see `io.cucumber.core.plugin.PrettyFormatter`.
  */
class Scenario(val delegate: TestCaseState) {

  /** @return tags of this scenario.
    */
  def getSourceTagNames: util.Collection[String] = delegate.getSourceTagNames

  /** Returns the current status of this test case.
    * <p>
    * The test case status is calculate as the most severe status of the
    * executed steps in the testcase so far.
    *
    * @return the current status of this test case
    */
  def getStatus: Status = Status.valueOf(delegate.getStatus.name)

  /** @return true if and only if `getStatus` returns "failed"
    */
  def isFailed: Boolean = delegate.isFailed

  /** Attach data to the report(s).
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

  /** Attaches some text based data to the report.
    *
    * @param data      what to attach, for example html.
    * @param mediaType what is the data?
    * @param name      attachment name
    * @see #attach(byte[], String, String)
    */
  def attach(data: String, mediaType: String, name: String): Unit = {
    delegate.attach(data, mediaType, name)
  }

  /** Outputs some text into the report.
    *
    * @param text what to put in the report.
    */
  def log(text: String): Unit = {
    delegate.log(text)
  }

  /** @return the name of the Scenario
    */
  def getName: String = delegate.getName

  /** Returns the unique identifier for this scenario.
    * <p>
    * If this is a Scenario from Scenario Outlines this will return the id of
    * the example row in the Scenario Outline.
    * <p>
    * The id is not stable across multiple executions of Cucumber but does
    * correlate with ids used in messages output. Use the uri + line number to
    * obtain a somewhat stable identifier of a scenario.
    *
    * @return the id of the Scenario.
    */
  def getId: String = delegate.getId

  /** @return the uri of the Scenario.
    */
  def getUri: URI = delegate.getUri

  /** Returns the line in the feature file of the Scenario.
    * <p>
    * If this is a Scenario from Scenario Outlines this will return the line of
    * the example row in the Scenario Outline.
    *
    * @return the line in the feature file of the Scenario
    */
  def getLine: Integer = delegate.getLine

}
