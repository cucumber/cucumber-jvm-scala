package io.cucumber.scala

import io.cucumber.datatable.DataTable

import scala.util.{Failure, Success, Try}
import scala.collection.JavaConverters._

trait AbstractDatatableElementTransformerDefinition extends AbstractGlueDefinition {

  val emptyPatterns: Seq[String]

  protected def replaceEmptyPatternsWithEmptyString(row: Seq[String]): Seq[String] = {
    row.map(replaceEmptyPatternsWithEmptyString)
  }

  protected def replaceEmptyPatternsWithEmptyString(table: DataTable): DataTable = {
    val rawWithEmptyStrings = table.cells().asScala
      .map(_.asScala.toSeq)
      .map(replaceEmptyPatternsWithEmptyString)
      .map(_.asJava)
      .toSeq
      .asJava

    DataTable.create(rawWithEmptyStrings, table.getTableConverter)
  }

  protected def replaceEmptyPatternsWithEmptyString(fromValue: Map[String, String]): Try[Map[String, String]] = {
    val replacement = fromValue.toSeq.map { case (key, value) =>
      val potentiallyEmptyKey = replaceEmptyPatternsWithEmptyString(key)
      val potentiallyEmptyValue = replaceEmptyPatternsWithEmptyString(value)

      (potentiallyEmptyKey, potentiallyEmptyValue)
    }

    if (containsDuplicateKey(replacement)) {
      Failure(createDuplicateKeyAfterReplacement(fromValue))
    } else {
      Success(replacement.toMap)
    }
  }

  protected def replaceEmptyPatternsWithEmptyString(t: String): String = {
    if (emptyPatterns.contains(t)) {
      ""
    } else {
      t
    }
  }

  private def containsDuplicateKey(seq: Seq[(String, Any)]): Boolean = {
    seq.map { case (key, _) => key }.toSet.size != seq.size
  }

  private def createDuplicateKeyAfterReplacement(fromValue: Map[String, String]): IllegalArgumentException = {
    val conflict = emptyPatterns.filter(emptyPattern => fromValue.contains(emptyPattern))
    val msg = s"After replacing ${conflict.headOption.getOrElse("")} and ${conflict.drop(1).headOption.getOrElse("")} with empty strings the datatable entry contains duplicate keys: $fromValue"
    new IllegalArgumentException(msg)
  }

}
