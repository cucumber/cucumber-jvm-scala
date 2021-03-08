import io.cucumber.gherkin.GherkinDialectProvider
import scala.jdk.CollectionConverters._

object I18nGenerator {

  private val dialectProvider = new GherkinDialectProvider()

  private val unsupported = Seq(
    "em"
  ) // The generated files for Emoji do not compile.
  private val allLanguages = dialectProvider
    .getLanguages()
    .asScala
    .filterNot(l => unsupported.contains(l))

  private def keywordVal(kw: String): String = {
    val keyworkValName = java.text.Normalizer
      .normalize(kw.replaceAll("[\\s',!]", ""), java.text.Normalizer.Form.NFC)
    s"""  val $keyworkValName = new Step("$keyworkValName")"""
  }

  private def traitCode(language: String): String = {
    val traitName = language.replaceAll("[\\s-]", "_").toUpperCase()
    val keywords = dialectProvider
      .getDialect(language, null)
      .getStepKeywords()
      .asScala
      .filter(kw => !kw.contains('*') && !kw.matches("^\\d.*"))
      .sorted
      .distinct

    s"""
       |trait $traitName {
       |  this: ScalaDsl =>
       |${keywords.map(kw => keywordVal(kw)).mkString("\n\n")}
       |}
       |""".stripMargin
  }

  val i18n: String = s"""
                |package io.cucumber.scala
                |
                |${allLanguages.map(l => traitCode(l)).mkString("\n\n")}
                |""".stripMargin

}
