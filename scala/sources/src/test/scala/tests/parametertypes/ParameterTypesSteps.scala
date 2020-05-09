package tests.parametertypes

import java.util.{List => JavaList}

import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}

import scala.jdk.CollectionConverters._

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

  DefaultParameterTransformer { (fromValue, toValueType) =>
    new StringBuilder().append(fromValue).append('-').append(toValueType)
  }

  DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType) =>
    new StringBuilder().append(fromValue).append("-").append(toValueType)
  }

  DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType) =>
    new StringBuilder().append(fromValue).append("-").append(toValueType)
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

  Given("kebab made from anonymous {}, defined by lambda") { (ingredients: StringBuilder) =>
    assert(ingredients.toString() == "meat-class scala.collection.mutable.StringBuilder")
  }

  Given("default data table cells, defined by lambda") { (dataTable: DataTable) =>
    val table = dataTable.asLists[StringBuilder](classOf[StringBuilder]).asScala.map(_.asScala)
    assert(table(0)(0).toString() == "Kebab-class scala.collection.mutable.StringBuilder")
    assert(table(1)(0).toString() == "-class scala.collection.mutable.StringBuilder")
  }

  Given("default data table cells, defined by lambda, as rows") { (cells: JavaList[JavaList[StringBuilder]]) =>
    val table = cells.asScala.map(_.asScala)
    assert(table(0)(0).toString() == "Kebab-class scala.collection.mutable.StringBuilder")
    assert(table(1)(0).toString() == "-class scala.collection.mutable.StringBuilder")
  }

  Given("default data table entries, defined by lambda") { (dataTable: DataTable) =>
    val table = dataTable.asList[StringBuilder](classOf[StringBuilder]).asScala
    assert(table(0).toString() == "Map(dinner -> Kebab)-class scala.collection.mutable.StringBuilder")
    assert(table(1).toString() == "Map(dinner -> )-class scala.collection.mutable.StringBuilder")
  }

  Given("default data table entries, defined by lambda, as rows") { (rows: JavaList[StringBuilder]) =>
    val table = rows.asScala
    assert(table(0).toString() == "Map(dinner -> Kebab)-class scala.collection.mutable.StringBuilder")
    assert(table(1).toString() == "Map(dinner -> )-class scala.collection.mutable.StringBuilder")
  }

}
