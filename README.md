# cucumber-jvm-scala
[![Maven Central](https://img.shields.io/maven-central/v/io.cucumber/cucumber-scala_2.12.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.cucumber%22%20AND%20a:%22cucumber-scala_2.12%22)
[![Build Status](https://travis-ci.org/cucumber/cucumber-jvm-scala.svg?branch=master)](https://travis-ci.org/cucumber/cucumber-jvm-scala)


## Dependency

### SBT
To use cucumber-jvm-scala in your project, add the following line to your `build.sbt`

```scala
libraryDependencies += "io.cucumber" %% "cucumber-scala" % "4.7.1" % Test
```

### Maven
To use cucumber-jvm-scala in your project, add the following dependency to your `pom.xml`:


```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-scala_2.12</artifactId>
    <version>4.7.1</version>
    <scope>test</scope>
</dependency>
```

## Compatibility matrix

The Cucumber Scala version matches the Cucumber version except for the bugfix number
which can be different.

| Cucumber Scala version | Cucumber version | Scala versions   |
|------------------------|------------------|------------------|
| 5.6.0                  | 5.6.0            | 2.11, 2.12, 2.13 |
| 4.7.1                  | 4.7.1            | 2.11, 2.12, 2.13 |

## Migrating from 4.x to 5.x

If you are using Cucumber Scala 4.7.x and want to upgrade to 5.x, please note there are some major changes in addition to the Cucumber upgrade itself.

### Packages

All Cucumber Scala classes are now under `io.cucumber.scala` package instead of `cucumber.api.scala`.

### Before/BeforeStep/After/AfterStep definitions

The `Before`, `BeforeStep`, `After` and `AfterStep` definitions have slightly changed:
- to apply only with some tags, the variable list of tags as `String*` is replaced by a single tag expression of type `String`.
- if providing both an order and a tag expression, the order is now the second parameter instead of the first.
This is more consistent with the Java implementation.

For instance, the following code:

```scala
Before(1, "@tag1", "@tag2") { _ =>
  // Do Something    
}
```

Is replaced by:

```scala
Before("@tag1 or @tag2", 1) { _ =>
  // Do Something    
}
```