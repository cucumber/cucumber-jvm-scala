package cucumber.runtime.scala.test

import java.util
import java.util.Locale

import cucumber.api.{TypeRegistry, TypeRegistryConfigurer}
import cucumber.runtime.scala.model.{Cukes, Person, Snake}
import io.cucumber.cucumberexpressions.{ParameterType, Transformer}
import io.cucumber.datatable.{DataTableType, TableEntryTransformer}

class TypeRegistryConfiguration extends TypeRegistryConfigurer {

  /**
    * Transforms an ASCII snake into an object, for example:
    *
    * {{{
    *  ====>  becomes Snake(length = 5, direction = 'east)
    *    ==>  becomes Snake(length = 3, direction = 'east)
    * }}}
    */
  private val snakeTransformer = new Transformer[Snake]() {
    def transform(s: String) = {
      val size = s.size
      val direction = s.toList match {
        case '<' :: _ => 'west
        case l if l.last == '>' => 'east
      }
      Snake(size, direction)
    }
  }

  private val personTransformer = new Transformer[Person]() {
    def transform(s: String) = {
      Person(s)
    }
  }

  private val booleanTransformer = new Transformer[Boolean]() {
    def transform(s: String) = {
      s.trim.equals("true")
    }
  }

  private val charTransformer = new Transformer[Char]() {
    def transform(s: String) = {
      s.charAt(0)
    }
  }

  private val listTransformer  =new TableEntryTransformer[Cukes]() {
    override def transform(map: util.Map[String, String]): Cukes = {
      new Cukes(map.get("Number").toInt, map.get("Color"))
    }
  }

  override def locale(): Locale = Locale.ENGLISH

  override def configureTypeRegistry(typeRegistry: TypeRegistry): Unit = {
    typeRegistry.defineParameterType(new ParameterType[Snake](
      "snake",
      "[=><]+",
      classOf[Snake],
      snakeTransformer
    ))

    typeRegistry.defineParameterType(new ParameterType[Person](
      "person",
      ".+",
      classOf[Person],
      personTransformer
    ))

    typeRegistry.defineParameterType(new ParameterType[Boolean](
      "boolean",
      "true|false",
      classOf[Boolean],
      booleanTransformer
    ))

    typeRegistry.defineParameterType(new ParameterType[Char](
      "char",
      ".",
      classOf[Char],
      charTransformer
    ))

    typeRegistry.defineDataTableType(new DataTableType(classOf[Cukes],listTransformer))
  }

}
