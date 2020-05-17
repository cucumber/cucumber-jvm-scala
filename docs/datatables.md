# DataTables

Cucumber Scala support DataTables with either:
- Scala types using a `DataTable` as step definition argument and implicit conversions by importing `import io.cucumber.scala.Implicits._`
- Java types in the step definitions arguments

**The benefit of using Scala types** if that you will be handling `Option`s instead of potentially `null` values in the Java collections.

See below the exhaustive list of possible mappings.

## As Map of Map

```gherkin
Given the following table as Map of Map
|      | key1  | key2  | key3  |
| row1 | val11 | val12 | val13 |
| row2 | val21 |       | val23 |
| row3 | val31 | val32 | val33 |
```

```scala
Given("the following table as Map of Map") { (table: DataTable) =>
  val scalaTable: Map[String, Map[String, Option[String]]] = table.asScalaRowColumnMap
  // Map(
  //    "row1" -> Map("key1" -> Some("val11"), "key2" -> Some("val12"), "key3" -> Some("val13")),
  //    "row2" -> Map("key1" -> Some("val21"), "key2" -> None, "key3" -> Some("val23")),
  //    "row3" -> Map("key1" -> Some("val31"), "key2" -> Some("val32"), "key3" -> Some("val33"))
  // )
}

// Or:
Given("the following table as Map of Map") { (table: JavaMap[String, JavaMap[String, String]]) =>
  // Map(
  //    "row1" -> Map("key1" -> "val11", "key2" -> "val12", "key3" -> "val13"),
  //    "row2" -> Map("key1" -> "val21", "key2" -> null, "key3" -> "val23"),
  //    "row3" -> Map("key1" -> "val31", "key2" -> "val32", "key3" -> "val33")
  // )
}
```

## As List of Map

```gherkin
Given the following table as List of Map
| key1  | key2  | key3  |
| val11 | val12 | val13 |
| val21 |       | val23 |
| val31 | val32 | val33 |
```

```scala
Given("the following table as List of Map") { (table: DataTable) =>
  val scalaTable: Seq[Map[String, Option[String]]] = table.asScalaMaps
  // Seq(
  //   Map("key1" -> Some("val11"), "key2" -> Some("val12"), "key3" -> Some("val13")),
  //   Map("key1" -> Some("val21"), "key2" -> None, "key3" -> Some("val23")),
  //   Map("key1" -> Some("val31"), "key2" -> Some("val32"), "key3" -> Some("val33"))
  // )
}

// Or:
Given("the following table as List of Map") { (table: JavaList[JavaMap[String, String]]) =>
  // Seq(
  //   Map("key1" -> "val11", "key2" -> "val12", "key3" -> "val13"),
  //   Map("key1" -> "val21", "key2" -> null, "key3" -> "val23"),
  //   Map("key1" -> "val31", "key2" -> "val32", "key3" -> "val33")
  // )
}
```

## As Map of List

```gherkin
Given the following table as Map of List
| row1 | val11 | val12 | val13 |
| row2 | val21 |       | val23 |
| row3 | val31 | val32 | val33 |
```

```scala
Given("the following table as Map of List") { (table: DataTable) =>
  val scalaTable: Map[Seq[Option[String]]] = table.asScalaRowMap
  // Map(
  //   "row1" -> Seq(Some("val11"), Some("val12"), Some("val13")),
  //   "row2" -> Seq(Some("val21"), None, Some("val23")),
  //   "row3" -> Seq(Some("val31"), Some("val32"), Some("val33"))
  // )
}

// Or:
Given("the following table as Map of List") { (table: JavaMap[String, JavaList[String]]) =>
  // Map(
  //   "row1" -> Seq("val11", "val12", "val13"),
  //   "row2" -> Seq("val21", null, "val23"),
  //   "row3" -> Seq("val31", "val32", "val33")
  // )
}
```


## As List of List

```gherkin
Given the following table as List of List
| val11 | val12 | val13 |
| val21 |       | val23 |
| val31 | val32 | val33 |
```

```scala
Given("the following table as List of List") { (table: DataTable) =>
  val scalaTable: Seq[Seq[Option[String]]] = table.asScalaLists
  // Seq(
  //   Seq(Some("val11"), Some("val12"), Some("val13")),
  //   Seq(Some("val21"), None, Some("val23")),
  //   Seq(Some("val31"), Some("val32"), Some("val33"))
  // )
}

// Or:
Given("the following table as List of List") { (table: JavaList[JavaList[String]]) =>
  // Seq(
  //   Seq("val11", "val12", "val13"),
  //   Seq("val21", null, "val23"),
  //   Seq("val31", "val32", "val33")
  // )
}
```

## As Map

```gherkin
Given the following table as Map
| row1 | val11 |
| row2 |       |
| row3 | val31 |
```

```scala
Given("the following table as Map") { (table: DataTable) =>
  val scalaTable: Map[String, Option[String]] = table.asScalaMap[String, String]
  // Map(
  //   "row1" -> Some("val11"),
  //   "row2" -> None,
  //   "row3" -> Some("val31")
  // )
}

// Or:
Given("the following table as Map") { (table: JavaMap[String, String]) =>
  // Map(
  //   "row1" -> "val11",
  //   "row2" -> null,
  //   "row3" -> "val31"
  // )
}
```

## As List

```gherkin
Given the following table as List
| val11 |
|       |
| val31 |
```

```scala
Given("the following table as List") { (table: DataTable) =>
  val scalaTable: Seq[Option[String]] = table.asScalaList
  // Seq(
  //   Some("val11"),
  //   None,
  //   Some("val31")
  // )
}

// Or:
Given("the following table as List") { (table: JavaList[String]) =>
  // Seq(
  //   "val11",
  //   null,
  //   "val31"
  // )
}
```

## As DataTable

```gherkin
Given the following table as DataTable
# Any previous table definition is possible
| key1  | key2  | key3  |
| val11 | val12 | val13 |
| val21 | val22 | val23 |
| val31 | val32 | val33 |
```

```scala
Given("the following table as DataTable") { (table: DataTable) =>
  // Use the table any way you want, including any of the options defined previously
}
```
