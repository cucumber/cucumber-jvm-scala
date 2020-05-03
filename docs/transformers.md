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

For instance, the following transformer can be defined:
```scala
case class Point(x: Int, y: Int)

ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
  Point(x.toInt, y.toInt)
}
```

And used like this:
```gherkin
Given balloon coordinates 123,456 in the game
```

```scala
Given("balloon coordinates {coordinates} in the game") { (coordinates: Point) =>
  // Do something with the coordinates
}
```

**Limitation:** there is a current limitation to 22 parameters in the `ParameterType` definition.

## DocString to any

`DocStringType` allows to transform DocString values (multiline string) to a custom type.

For instance, the following transformer can be defined:
```scala
case class JsonText(json: String)

DocStringType("json") { text =>
  JsonText(text)
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

```scala
Given("the following json text") { json: JsonText =>
  // Do something with JsonText
}
```

## DataTable to any

`DataTableType` allows to transform DataTable to a custom type.

This can be achieved in different ways:
- transform lines with named headers to any type
- transform lines without headers to any type
- transform tables to any type
- transform cells content to any type

Note that DataTables in Gherkin can not represent `null` or the empty string unambiguously.
Cucumber will interpret empty cells as `null`.
But you can use a replacement to represent empty strings.
See below.

See also the [Datatable reference](https://github.com/cucumber/cucumber/tree/master/datatable).

### Lines with named headers

For instance, the following transformer can be defined:
```scala
case class Author(name: String, surname: String, famousBook: String)

DataTableType { entry: Map[String, String] =>
  Author(entry("name"), entry("surname"), entry("famousBook"))
}
```

And used like this:
```gherkin
Given the following authors
| name   | surname | famousBook      |
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

```scala
Given("the following authors") { (authors: java.util.List[Author]) =>
  // Do something
}

// Or using DataTable
Given("the following authors") { (table: DataTable) =>
  val authors = table.asList[Author](classOf[Author])
}
```

### Lines without headers

For instance, the following transformer can be defined:
```scala
case class Author(name: String, surname: String, famousBook: String)

DataTableType { row: Seq[String] =>
  Author(row(0), row(1), row(2))
}
```

And used like this:
```gherkin
Given the following authors
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

```scala
Given("the following authors") { (authors: java.util.List[Author]) =>
  // Do something
}

// Or using DataTable
Given("the following authors") { (table: DataTable) =>
  val authors = table.asList[Author](classOf[Author])
}
```

### DataTable

For instance, the following transformer can be defined:
```scala
case class Author(name: String, surname: String, famousBook: String)
case class GroupOfAuthor(authors: Seq[Author])

DataTableType { table: DataTable =>
  val authors = table.asMaps().asScala
      .map(_.asScala)
      .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
      .toSeq
  GroupOfAuthor(authors)
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

```scala
Given("the following authors") { (table: DataTable) =>
  val authors = table.convert[GroupOfAuthor](classOf[GroupOfAuthor], false)
}
```

### Cell

For instance, the following transformer can be defined:
```scala
case class RichCell(content: String)

DataTableType { cell: String =>
  RichCell(cell)
}
```

And used like this:
```gherkin
Given the following authors
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

```scala
Given("the following authors") { (authors: java.util.List[java.util.List[RichCell]]) =>
  // Do something
}

// Or using DataTable
Given("the following authors") { (table: DataTable) =>
  val authors = table.asLists[RichCell](classOf[RichCell]))
}
```

Or with headers like this:
```gherkin
Given the following authors
| name   | surname | famousBook      |
| Alan   | Alou    | The Lion King   |
| Robert | Bob     | Le Petit Prince |
```

```scala
Given("the following authors") { (authors: java.util.List[java.util.Map[String, RichCell]]) =>
  // Do something
}

// Or using DataTable
Given("the following authors") { (table: DataTable) =>
  val authors = table.asMaps[String, RichCell](classOf[String], classOf[RichCell])
}
```

### Empty values

By default empty values in DataTable are treated as `null` by Cucumber.
If you need to have empty values, you can define a replacement like `[empty]` that will be automatically replaced to empty when parsing DataTable.

To do so, you can add a parameter to a `DataTableType` definition.

For instance, with the following definition:
```scala
case class Author(name: String, surname: String, famousBook: String)

DataTableType("[empty]") { (entry: Map[String, String]) =>
  Author(entry("name"), entry("surname"), entry("famousBook"))
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

For instance, the following definition:
```scala
DefaultParameterTransformer { (fromValue: String, toValueType: java.lang.Type) =>
  // Apply logic to convert from String to toValueType
}
```

Will be used to convert with such step definitions:
```scala
Given("A step with a undefined {} string") { (param: SomeType) =>
  // The string between {} will be converted to SomeType
}
```

### DataTable

#### Lines with named headers

For instance the following definition:
```scala
DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType: java.lang.Type) =>
  // Apply some logic to convert from Map to toValueType
}
```

Will be used to convert with such step definitions:
```scala
Given("A step with a datatable") { (rows: java.util.List[SomeType]) =>
  // Do something
}

// Or DataTable
Given("A step with a datatable") { (dataTable: DataTable) =>
  val table = dataTable.asList[SomeType](classOf[SomeType])
}
```

This is what to `DefaultJacksonDataTableTransformer` uses.

#### Cells

For instance the following definition:
```scala
DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType: java.lang.Type) =>
  // Apply some logic to convert from String to toValueType
}
```

Will be used to convert with such step definitions:
```scala
Given("A step with a datatable") { (rows: java.util.List[java.util.List[SomeType]]) =>
  // Do something
}

// Or DataTable
Given("A step with a datatable") { (dataTable: DataTable) =>
  val table = dataTable.asLists[SomeType](classOf[SomeType])
}
```
