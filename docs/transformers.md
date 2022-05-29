# Transformers

Transformers are glue code like step definitions or hooks.
You have to define them in your glue classes.

Cucumber allows the following specific transformers:
- String to any type
- DocString (multiline string) to any type
- DataTable to any type
  - transform lines with named headers to any type
  - transform lines without headers to any type
  - transform tables to any type
  - transform cells content to any type

As well as default transformers for:
- String
- DataTable
  - lines with named headers
  - cells

## String to any

`ParameterType` allows to transform a String value from a Cucumber expression to a custom type.

It is defined by a name (used in the steps definitions) and a regex.
Each group of the regex will map to a parameter of the transformation function.

For instance, the following transformer and step can be defined:
```scala mdoc:compile-only
import io.cucumber.scala.{EN, ScalaDsl}

case class Point(x: Int, y: Int)

class Steps extends ScalaDsl with EN {

  ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
    Point(x.toInt, y.toInt)
  }
  
  Given("balloon coordinates {coordinates} in the game") { (coordinates: Point) =>
    // Do something with the coordinates
  }

}
```

And used like this:
```gherkin
Given balloon coordinates 123,456 in the game
```

**Limitation:** there is a current limitation to 22 parameters in the `ParameterType` definition.

## DocString to any

`DocStringType` allows to transform DocString values (multiline string) to a custom type.

For instance, the following transformer and step can be defined:
```scala mdoc:compile-only
import io.cucumber.scala.{EN, ScalaDsl}

case class JsonText(json: String)

class Steps extends ScalaDsl with EN {

  DocStringType("json") { text =>
    JsonText(text)
  }
  
  Given("the following json text") { json: JsonText =>
    // Do something with JsonText
  }

}
```

And used like this:
```gherkin
Given the following json text
"""json
{
"key": "value"
}
"""
```

## DataTable to any

`DataTableType` allows to transform DataTable to a custom type.

This can be achieved in different ways:
- transform lines with named headers to any type
- transform lines without headers to any type
- transform tables to any type
- transform cells content to any type

Note that DataTables in Gherkin can not represent `null` or the empty string unambiguously.
Cucumber will interpret empty cells as `None` or `null`.
But you can use a replacement to represent empty strings.
See below.

See also the [Datatable reference](https://github.com/cucumber/cucumber/tree/master/datatable).

### Lines with named headers

For instance, the following transformer and step can be defined:
```scala mdoc:compile-only
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.scala.Implicits._

case class Author(name: String, surname: String, famousBook: String)

class Steps extends ScalaDsl with EN {

  DataTableType { entry: Map[String, Option[String]] => // Or Map[String, String]
    Author(entry("name").getOrElse("NoValue"), entry("surname").getOrElse("NoValue"), entry("famousBook").getOrElse("NoValue"))
  }
  
  Given("the following authors") { (table: DataTable) =>
    val authors = table.asScalaRawList[Author]
  }
  
  // Or using Java type
  Given("the following authors") { (authors: java.util.List[Author]) =>
    // Do something
  }

}
```

And used like this:
```gherkin
Given the following authors
| name   | surname | famousBook      |
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

### Lines without headers

For instance, the following transformer and step can be defined:
```scala mdoc:compile-only
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.scala.Implicits._

case class Author(name: String, surname: String, famousBook: String)

class Steps extends ScalaDsl with EN {

  DataTableType { row: Seq[Option[String]] => // Or Seq[String]
    Author(row(0).getOrElse("NoValue"), row(1).getOrElse("NoValue"), row(2).getOrElse("NoValue"))
  }
  
  Given("the following authors") { (table: DataTable) =>
    val authors = table.asScalaRawList[Author]
  }
  
  // Or using Java types
  Given("the following authors") { (authors: java.util.List[Author]) =>
    // Do something
  }

}
```

And used like this:
```gherkin
Given the following authors
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

### DataTable

For instance, the following transformer and step can be defined:
```scala mdoc:compile-only
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.scala.Implicits._
import scala.jdk.CollectionConverters._

case class Author(name: String, surname: String, famousBook: String)
case class GroupOfAuthor(authors: Seq[Author])

class Steps extends ScalaDsl with EN {

  DataTableType { table: DataTable =>
    val authors = table.entries().asScala
        .map(_.asScala)
        .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
        .toSeq
    GroupOfAuthor(authors)
  }
  
  Given("the following authors") { (table: DataTable) =>
    val authors = table.convert[GroupOfAuthor](classOf[GroupOfAuthor], false)
  }

}
```

_Please note that the same transformation could be done using a line transformer._
_The purpose of this transformer is to show the syntax._

And used like this:
```gherkin
Given the following authors
| name   | surname | famousBook      |
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

### Cell

For instance, the following transformer and step can be defined:
```scala mdoc:compile-only
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.scala.Implicits._

case class RichCell(content: String)

class Steps extends ScalaDsl with EN {

  DataTableType { cell: Option[String] => // Or String
    RichCell(cell.getOrElse("NoValue"))
  }

  Given("the following authors") { (table: DataTable) =>
    val authors = table.asScalaRawLists[RichCell]
  }
  
  // Or using Java types
  Given("the following authors") { (authors: java.util.List[java.util.List[RichCell]]) =>
    // Do something
  }
  
  Given("the following authors with headers") { (table: DataTable) =>
    val authors = table.asScalaRawMaps[String, RichCell]
  }
  
  // Or with Java Types
  Given("the following authors with headers") { (authors: java.util.List[java.util.Map[String, RichCell]]) =>
    // Do something
  }

}
```

And used like this:
```gherkin
Given the following authors
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

Or with headers like this:
```gherkin
Given the following authors with headers
| name   | surname | famousBook      |
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

### Empty values

By default empty values in DataTable are treated as `None` or `null` by Cucumber.
If you need to have empty values, you can define a replacement like `[empty]` that will be automatically replaced to empty when parsing DataTable.

To do so, you can add a parameter to a `DataTableType` definition.

For instance, with the following definition:
```scala mdoc:compile-only
import io.cucumber.scala.{EN, ScalaDsl}

case class Author(name: String, surname: String, famousBook: String)

class Steps extends ScalaDsl with EN {

  DataTableType("[empty]") { (entry: Map[String, String]) => // Or Map[String, Option[String]]
    Author(entry("name"), entry("surname"), entry("famousBook"))
  }

}
```

And the following step:
```gherkin
Given the following authors
| name    | surname | famousBook      |
| Alan    | Alou    | The Lion King   |
| [empty] | Bob     | Le Petit Prince |
```

You would actually get a list containing `Author("Alan", "Alou", "The Lion King")` and `Author("", "Bob", "Le Petit Prince")`.

## Default transformers

Default transformers are used when there is no specific transformer.

They can be used with object mappers like Jackson to easily convert from well known strings to objects.

See also [Default Jackson DataTable Transformer](default_jackson_datatable_transformer.md).

### String

For instance, the following definition will be used to convert such step definitions:
```scala mdoc:compile-only
import io.cucumber.scala.{EN, ScalaDsl}

class SomeType {}

class Steps extends ScalaDsl with EN {

  DefaultParameterTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
    // Apply logic to convert from String to toValueType
    if (toValueType == classOf[SomeType]) {
      new SomeType() // Use fromValue somehow
    } else {
      null
    }
  }
  
  Given("A step with a undefined {} string") { (param: SomeType) =>
    // The string between {} will be converted to SomeType
  }

}
```

### DataTable

#### Lines with named headers

For instance the following definition will be used to convert such step definitions:
```scala mdoc:compile-only
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.scala.Implicits._

class SomeType {}

class Steps extends ScalaDsl with EN {

  DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
    // Apply some logic to convert from Map to toValueType
    if (toValueType == classOf[SomeType]) {
      new SomeType() // Use fromValue somehow
    } else {
      null
    }
  }
  
  Given("A step with a datatable") { (dataTable: DataTable) =>
    val table = dataTable.asScalaRawList[SomeType]
  }
  
  // Or with Java types
  Given("A step with a datatable") { (rows: java.util.List[SomeType]) =>
    // Do something
  }

}
```

This is what to `DefaultJacksonDataTableTransformer` uses.

#### Cells

For instance the following definition will be used to convert such step definitions:
```scala mdoc:compile-only
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}
import io.cucumber.scala.Implicits._

class SomeType {}

class Steps extends ScalaDsl with EN {

  DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType: java.lang.reflect.Type) =>
    // Apply some logic to convert from String to toValueType
    if (toValueType == classOf[SomeType]) {
      new SomeType() // Use fromValue somehow
    } else {
      null
    }
  }
  
  Given("A step with a datatable") { (dataTable: DataTable) =>
    val table = dataTable.asScalaRawLists[SomeType]
  }
  
  // Or with Java Types
  Given("A step with a datatable") { (rows: java.util.List[java.util.List[SomeType]]) =>
    // Do something
  }

}
```
