# Default Jackson DataTable Transformer

Cucumber Scala provides an optional Default DataTable Transformer that uses Jackson.

It can be used to automatically convert DataTables to case classes without defining custom converters.

## Add Jackson dependency

To use this optional transformer, you need to have Jackson Scala in your dependencies.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.module</groupId>
    <artifactId>jackson-module-scala_2.13</artifactId>
    <version>2.10.3</version>
    <scope>test</scope>
</dependency>
```

Or:
```sbt
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.3" % Test
```


The current version of Cucumber Scala has been tested against Jackson Module Scala **version 2.10.3**.

## Add the transformer

The transformer has to be added to your glue code by extending the `JacksonDefaultDataTableEntryTransformer` trait.

For instance:
```scala
class MySteps extends ScalaDsl with EN with JacksonDefaultDataTableEntryTransformer {
  // Your usual glue code
}
```

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
