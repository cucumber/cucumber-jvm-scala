# Default Jackson DataTable Transformer

Cucumber Scala provides an optional Default DataTable Transformer that uses Jackson.

It can be used to automatically convert DataTables to case classes without defining custom converters.

## Add Jackson dependency

### Jackson 2.x

To use this optional transformer, you need to have Jackson Scala in your dependencies.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.module</groupId>
    <artifactId>jackson-module-scala_2.13</artifactId>
    <version>2.20.0</version>
    <scope>test</scope>
</dependency>
```

Or:
```sbt
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.20.0" % Test
```

The current version of Cucumber Scala has been tested against Jackson Module Scala **version 2.20.0**.

### Jackson 3.x

To use this optional transformer, you need to have Jackson Scala in your dependencies.

```xml
<dependency>
    <groupId>tools.jackson.module</groupId>
    <artifactId>jackson-module-scala_2.13</artifactId>
    <version>3.0.0</version>
    <scope>test</scope>
</dependency>
```

Or:
```sbt
libraryDependencies += "tools.jackson.module" %% "jackson-module-scala" % "3.0.0" % Test
```

The current version of Cucumber Scala has been tested against Jackson Module Scala **version 3.0.0**.

## Add the transformer

The transformer has to be added to your glue code by extending the `JacksonDefaultDataTableEntryTransformer` (Jackson 2.x)
or `Jackson3DefaultDataTableEntryTransformer` (Jackson 3.x) trait.

For instance:
```scala
class MySteps extends ScalaDsl with EN with Jackson3DefaultDataTableEntryTransformer {
  // Your usual glue code
}
```

Note that it should be included only once in your glue code. If you use multiple glue classes, either add it to only one of them or add it to a separate `object`.

### Empty string replacement

The default empty string replacement used by the default transformer is `[empty]`.

You can override it if you need to:
```scala
override def emptyStringReplacement: String = "[blank]"
```

## Example

Then, let the transformer do its work!

For instance, the following DataTable:
```gherkin
Given I have the following datatable
| field1 | field2 | field3 |
| 1.2    | true   | abc    |
| 2.3    | false  | def    |
| 3.4    | true   | ghj    |
```

will be automatically converted to the following case class:
```scala
case class MyCaseClass(field1: Double, field2: Boolean, field3: String)

Given("I have the following datatable") { (data: java.util.List[MyCaseClass]) =>
  // Do something
}
```
