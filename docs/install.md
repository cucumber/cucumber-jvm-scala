# Installation

## Dependency

### SBT

To use Cucumber Scala in your project, add the following line to your `build.sbt`:

```scala
libraryDependencies += "io.cucumber" %% "cucumber-scala" % "9.2.1" % Test
```

And optionally, the ScalaTest integration:
```scala
libraryDependencies += "io.cucumber" %% "cucumber-scalatest" % "9.2.1" % Test
```

### Maven

To use Cucumber Scala in your project, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-scala_3</artifactId>
    <version>9.2.1</version>
    <scope>test</scope>
</dependency>
```

And optionally, the ScalaTest integration:
```scala
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-scalatest_3</artifactId>
    <version>9.2.1</version>
    <scope>test</scope>
</dependency>
```
