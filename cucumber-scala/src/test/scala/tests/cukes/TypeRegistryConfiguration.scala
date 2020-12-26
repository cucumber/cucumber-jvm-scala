package tests.cukes

import io.cucumber.scala.ScalaDsl
import tests.cukes.model.{Cukes, Person, Snake}

class TypeRegistryConfiguration extends ScalaDsl {

  /** Transforms an ASCII snake into an object, for example:
    *
    * {{{
    *  ====>  becomes Snake(length = 5, direction = 'east)
    *    ==>  becomes Snake(length = 3, direction = 'east)
    * }}}
    */
  ParameterType("snake", "[=><]+") { s =>
    val size = s.length
    val direction = s.toList match {
      case '<' :: _           => Symbol("west")
      case l if l.last == '>' => Symbol("east")
      case _                  => Symbol("unknown")
    }
    Snake(size, direction)
  }

  ParameterType("person", ".+") { s =>
    Person(s)
  }

  ParameterType("boolean", "true|false") { s =>
    s.trim.equals("true")
  }

  ParameterType("char", ".") { s =>
    s.charAt(0)
  }

  DataTableType { (map: Map[String, String]) =>
    Cukes(map("Number").toInt, map("Color"))
  }

}
