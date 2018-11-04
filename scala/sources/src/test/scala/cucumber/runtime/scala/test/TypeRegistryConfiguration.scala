package cucumber.runtime.scala.test

import java.lang.reflect.Type
import java.util
import java.util.Locale
import cucumber.api.{TypeRegistry, TypeRegistryConfigurer}
import cucumber.runtime.scala.model.{Cukes, Person, Snake}
import io.cucumber.cucumberexpressions.{ParameterByTypeTransformer, ParameterType, Transformer}
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.datatable.{DataTableType, TableCellByTypeTransformer, TableEntryByTypeTransformer, TableEntryTransformer}

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
    val defaultTransformer = new DefaultTransformer()
    typeRegistry.setDefaultDataTableCellTransformer(defaultTransformer)
    typeRegistry.setDefaultDataTableEntryTransformer(defaultTransformer)
    typeRegistry.setDefaultParameterTransformer(defaultTransformer)

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
  private class DefaultTransformer
    extends ParameterByTypeTransformer
      with TableEntryByTypeTransformer
      with TableCellByTypeTransformer {

    var objectMapper: ObjectMapper = new ObjectMapper()

    override def transform(s: String, `type`: Type): AnyRef =
      objectMapper.convertValue(s, objectMapper.constructType(`type`))


    override def transform[T](s: String, aClass: Class[T]): T =
      objectMapper.convertValue(s, aClass)

    override def transform[T](map: util.Map[String, String],
                              aClass: Class[T],
                              tableCellByTypeTransformer: TableCellByTypeTransformer): T =
      objectMapper.convertValue(map, aClass)
  }

}
