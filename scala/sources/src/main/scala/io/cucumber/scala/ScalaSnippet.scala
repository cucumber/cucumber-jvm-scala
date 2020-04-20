package io.cucumber.scala

import java.lang.reflect.Type
import java.text.MessageFormat
import java.util.{Map => JMap}

import io.cucumber.core.backend.Snippet
import io.cucumber.datatable.DataTable

import scala.collection.JavaConverters._

object ScalaSnippet {

  // Allows to use """ in """xxx"""" strings
  val tripleDoubleQuotes = "\"\"\""

}

class ScalaSnippet extends Snippet {

  import ScalaSnippet.tripleDoubleQuotes

  override def template(): MessageFormat = {
    new MessageFormat(
      s"""{0}(${tripleDoubleQuotes}{1}${tripleDoubleQuotes}) '{' ({3}) =>
         |  // {4}
         |  throw new ${classOf[PendingException].getName}()
         |'}'""".stripMargin)
  }

  override def tableHint(): String = {
    """|     // For automatic transformation, change DataTable to one of
       |     // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
       |     // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
       |     // Double, Byte, Short, Long, BigInteger or BigDecimal.
       |     //
       |     // For other transformations you can register a DataTableType.""".stripMargin
  }

  override def escapePattern(pattern: String): String = pattern

  override def arguments(map: JMap[String, Type]): String = {
    map.asScala
      .map { case (argName, argType) => s"$argName: ${getArgType(argType)}" }
      .mkString(", ")
  }

  private def getArgType(argType: Type): String = {
    argType match {
      // Scala classes
      // TODO is there a native Scala way of doing so?
      case cType: Class[_] if cType == classOf[java.lang.Integer] => "Int"
      case cType: Class[_] if cType == classOf[java.lang.Long] => "Long"
      case cType: Class[_] if cType == classOf[java.lang.Float] => "Float"
      case cType: Class[_] if cType == classOf[java.lang.Double] => "Double"
      // Java behavior
      case cType: Class[_] if cType == classOf[DataTable] => cType.getName
      case cType: Class[_] => cType.getSimpleName
      case _ => argType.toString
    }
  }

}
