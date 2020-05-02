package tests.parametertypes

import io.cucumber.scala.{EN, ScalaDsl}

case class Point(x: Int, y: Int)

class ParameterTypesSteps extends ScalaDsl with EN {

  ParameterType("string-builder", ".*") { str =>
    new StringBuilder(str)
  }

  ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
    Point(x.toInt, y.toInt)
  }

  ParameterType("ingredients", "(.+), (.+) and (.+)") { (x, y, z) =>
    s"-$x-$y-$z-"
  }

  Given("{string-builder} parameter, defined by lambda") { builder: StringBuilder =>
    assert(builder.toString() == "string builder")
  }

  Given("balloon coordinates {coordinates}, defined by lambda") { (coordinates: Point) =>
    assert(coordinates == Point(123, 456))
  }

  Given("kebab made from {ingredients}, defined by lambda") { (ingredients: String) =>
    assert(ingredients == "-mushroom-meat-veg-")
  }

}
