package cucumber.runtime.scala

import java.lang.reflect.Type
import java.util

import cucumber.runtime.snippets.Snippet

import collection.JavaConverters._

class ScalaSnippetGenerator extends Snippet {

  def template() =
    "{0}(\"\"\"{1}\"\"\")'{' ({3}) =>\n" +
      "  //// {4}\n" +
      "  throw new PendingException()\n" +
      "'}'"

  def tableHint() = null

  def namedGroupStart() = null

  def namedGroupEnd() = null

  def escapePattern(pattern:String) = pattern

  override def arguments(map: util.Map[String, Type]): String = {
    val indexed = map.asScala.zipWithIndex

    def name(clazz: Class[_]) =
      if(clazz.isPrimitive){
        val name = clazz.getName
        s"${name.charAt(0).toUpper}${name.substring(1)}"
      } else
        clazz.getSimpleName

    val named = indexed.map {
      case (c, i) => "arg" + i + ":" + name(c._2.getClass)
    }

    named.mkString(", ")
  }
}
