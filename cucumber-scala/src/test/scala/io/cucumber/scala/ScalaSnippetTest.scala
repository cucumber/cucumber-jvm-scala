package io.cucumber.scala

import java.util
import java.util.Locale

import io.cucumber.core.gherkin.Step
import io.cucumber.core.snippets.{SnippetGenerator, SnippetType}
import io.cucumber.cucumberexpressions.{
  CaptureGroupTransformer,
  ParameterType,
  ParameterTypeRegistry,
  TypeReference
}
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import scala.jdk.CollectionConverters._

class ScalaSnippetTest {

  private val snippetType = SnippetType.UNDERSCORE

  @Test
  def generatesPlainSnippet() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {int} cukes in my {string} belly${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int, string: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("I have 4 cukes in my \"big\" belly"))
  }

  @Test
  def generatesPlainSnippetUsingCustomParameterTypes() = {
    val customParameterType = new ParameterType[Size](
      "size",
      "small|medium|large",
      classOf[Size],
      new CaptureGroupTransformer[Size] {
        override def transform(groups: Array[String]): Size = null
      },
      true,
      false
    )

    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {double} cukes in my {size} belly${ScalaSnippet.tripleDoubleQuotes}) { (double1: Double, size: Size) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin

    assertEquals(
      expected,
      snippetFor("I have 4.2 cukes in my large belly", customParameterType)
    )
  }

  @Test
  def generatesPlainSnippetUsingComplexParameterTypes() = {
    val customParameterType = new ParameterType[Size](
      "sizes",
      List("(small|medium|large)(( and |, )(small|medium|large))*").asJava,
      new TypeReference[util.List[Size]]() {}.getType,
      new CaptureGroupTransformer[Size] {
        override def transform(groups: Array[String]): Size = null
      },
      true,
      false
    )

    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {sizes} bellies${ScalaSnippet.tripleDoubleQuotes}) { (sizes: java.util.List<io.cucumber.scala.ScalaSnippetTest$$Size>) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin

    assertEquals(
      expected,
      snippetFor("I have large and small bellies", customParameterType)
    )
  }

  @Test
  def generatesCopyPasteReadyStepSnippetForNumberParameters() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}before {int} after${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("before 5 after"))
  }

  @Test
  def generatesCopyPasteReadySnippetWhenStepHasIllegalJavaIdentifierChars() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {int} cukes in: my {string} red-belly!${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int, string: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(
      expected,
      snippetFor("I have 4 cukes in: my \"big\" red-belly!")
    )
  }

  @Test
  def generatesCopyPasteReadySnippetWhenStepHasIntegersInsideStringParameter() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}the DI system receives a message saying {string}${ScalaSnippet.tripleDoubleQuotes}) { (string: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(
      expected,
      snippetFor(
        "the DI system receives a message saying \"{ dataIngestion: { feeds: [ feed: { merchantId: 666, feedId: 1, feedFileLocation: feed.csv } ] }\""
      )
    )
  }

  @Test
  def generatesSnippetWithQuestionMarks() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}is there an error?:${ScalaSnippet.tripleDoubleQuotes}) { () =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("is there an error?:"))
  }

  @Test
  def generatesSnippetWithLotsOfNonIdentifierCharacters() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}\\([a-z]*)?.+${ScalaSnippet.tripleDoubleQuotes}) { () =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("([a-z]*)?.+"))
  }

  @Test
  def generatesSnippetWithParentheses() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {int} cukes \\(maybe more)${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("I have 5 cukes (maybe more)"))
  }

  @Test
  def generatesSnippetWithBrackets() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {int} cukes [maybe more]${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("I have 5 cukes [maybe more]"))
  }

  @Test
  def generatesSnippetWithDocString() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have:${ScalaSnippet.tripleDoubleQuotes}) { (docString: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetForDocString("I have:", "hello"))
  }

  @Test
  def generatesSnippetWithMultipleArgumentsNamedDocString() = {
    val customParameterType = new ParameterType[String](
      "docString",
      "\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"",
      classOf[String],
      new CaptureGroupTransformer[String] {
        override def transform(strings: Array[String]): String = null
      },
      true,
      false
    )

    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have a {docString}:${ScalaSnippet.tripleDoubleQuotes}) { (docString: String, docString1: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}
         |Given(${ScalaSnippet.tripleDoubleQuotes}I have a {string}:${ScalaSnippet.tripleDoubleQuotes}) { (string: String, docString: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(
      expected,
      snippetForDocString(
        "I have a \"Documentation String\":",
        "hello",
        customParameterType
      )
    )
  }

  @Test
  def generatesSnippetWithDataTable() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have:${ScalaSnippet.tripleDoubleQuotes}) { (dataTable: io.cucumber.datatable.DataTable) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetForDataTable("I have:"))
  }

  @Test
  def generatesSnippetWithMultipleArgumentsNamedDataTable() = {
    val customParameterType = new ParameterType[String](
      "dataTable",
      "\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"",
      classOf[String],
      new CaptureGroupTransformer[String] {
        override def transform(strings: Array[String]): String = null
      },
      true,
      false
    )

    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have in table {dataTable}:${ScalaSnippet.tripleDoubleQuotes}) { (dataTable: String, dataTable1: io.cucumber.datatable.DataTable) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}
         |Given(${ScalaSnippet.tripleDoubleQuotes}I have in table {string}:${ScalaSnippet.tripleDoubleQuotes}) { (string: String, dataTable: io.cucumber.datatable.DataTable) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(
      expected,
      snippetForDataTable("I have in table \"M6\":", customParameterType)
    )
  }

  @Test
  def generateSnippetWithOutlineParam() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}Then it responds <param>${ScalaSnippet.tripleDoubleQuotes}) { () =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(expected, snippetFor("Then it responds <param>"))
  }

  @Test
  def generatesSnippetUsingFirstGivenWhenThenKeyWord() = {
    val expected =
      s"""When(${ScalaSnippet.tripleDoubleQuotes}I have {int} cukes in my {string} belly${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int, string: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(
      expected,
      snippetForWhenAnd("I have 4 cukes in my \"big\" belly")
    )
  }

  @Test
  def generatesSnippetDefaultsToGiven() = {
    val expected =
      s"""Given(${ScalaSnippet.tripleDoubleQuotes}I have {int} cukes in my {string} belly${ScalaSnippet.tripleDoubleQuotes}) { (int1: Int, string: String) =>
         |  // Write code here that turns the phrase above into concrete actions
         |  throw new io.cucumber.scala.PendingException()
         |}""".stripMargin
    assertEquals(
      expected,
      snippetForWildCard("I have 4 cukes in my \"big\" belly")
    )
  }

  private def getSnippet(
      step: Step,
      parameterType: Option[ParameterType[_]] = None
  ): String = {
    val parameterTypeRegistry = new ParameterTypeRegistry(Locale.ENGLISH)
    parameterType.foreach { pt =>
      parameterTypeRegistry.defineParameterType(pt)
    }
    val snippet = new SnippetGenerator(new ScalaSnippet, parameterTypeRegistry)
      .getSnippet(step, snippetType)
      .asScala
    snippet.mkString("\n")
  }

  private def snippetFor(stepText: String): String = {
    val step = createStep(stepText)
    getSnippet(step)
  }

  private def snippetFor(
      stepText: String,
      parameterType: ParameterType[_]
  ): String = {
    val step = createStep(stepText)
    getSnippet(step, Some(parameterType))
  }

  private def snippetForDocString(
      stepText: String,
      docString: String
  ): String = {
    val step = createStepWithDocString(stepText, docString)
    getSnippet(step)
  }

  private def snippetForDocString(
      stepText: String,
      docString: String,
      parameterType: ParameterType[_]
  ): String = {
    val step = createStepWithDocString(stepText, docString)
    getSnippet(step, Some(parameterType))
  }

  private def snippetForDataTable(stepText: String): String = {
    val step = createStepWithDataTable(stepText)
    getSnippet(step)
  }

  private def snippetForDataTable(
      stepText: String,
      parameterType: ParameterType[_]
  ): String = {
    val step = createStepWithDataTable(stepText)
    getSnippet(step, Some(parameterType))
  }

  private def snippetForWhenAnd(stepText: String): String = {
    val source =
      s"""Feature: Test feature
         |  Scenario: Test Scenario
         |  When some other step
         |  And ${stepText}
         |""".stripMargin

    val feature = TestFeatureParser.parse(source)
    val step = feature.getPickles.asScala.head.getSteps.asScala(1)

    getSnippet(step)
  }

  private def snippetForWildCard(stepText: String): String = {
    val source =
      s"""Feature: Test feature
         |  Scenario: Test Scenario
         |  * ${stepText}
         |""".stripMargin

    val feature = TestFeatureParser.parse(source)
    val step = feature.getPickles.asScala.head.getSteps.asScala.head

    getSnippet(step)
  }

  private def createStep(stepText: String): Step = {
    val source =
      s"""Feature: Test feature
         |  Scenario: Test Scenario
         |  Given ${stepText}
         |""".stripMargin

    val feature = TestFeatureParser.parse(source)
    feature.getPickles.asScala.head.getSteps.asScala.head
  }

  private def createStepWithDocString(
      stepText: String,
      docString: String
  ): Step = {
    val source =
      s"""Feature: Test feature
         |  Scenario: Test Scenario
         |  Given ${stepText}
         |  ${ScalaSnippet.tripleDoubleQuotes}
         |  ${docString}
         |  ${ScalaSnippet.tripleDoubleQuotes}
         |""".stripMargin

    val feature = TestFeatureParser.parse(source)
    feature.getPickles.asScala.head.getSteps.asScala.head
  }

  private def createStepWithDataTable(stepText: String): Step = {
    val source =
      s"""Feature: Test feature
         |  Scenario: Test Scenario
         |  Given ${stepText}
         |    | key   |
         |    | value |
         |""".stripMargin

    val feature = TestFeatureParser.parse(source)
    feature.getPickles.asScala.head.getSteps.asScala.head
  }

  private class Size {
    // Dummy. Makes the test readable
  }

}
