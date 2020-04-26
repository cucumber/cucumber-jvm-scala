# Project structure

The Cucumber Scala project is a Maven multimodule project:
- `scala` module: contains the codebase of the Cucumber Scala implementation
  - `scala_2.11` submodule: build for Scala 2.11.x
  - `scala_2.12` submodule: build for Scala 2.12.x
  - `scala_2.13` submodule: build for Scala 2.13.x
- `examples` module: contains a sample project 

## Cross compilation

The cross compilation for the different Scala versions is handled with 3 different Maven projects: the submodules of the `scala` module.

Each project has a different Scala version as dependency:
```xml
<dependency>
    <groupId>org.scala-lang</groupId>
    <artifactId>scala-compiler</artifactId>
    <version>${scala.2.13.version}</version>
    <scope>provided</scope>
</dependency>
```

To not copy/paste the sources across the 3 projects, the sources are put in a separated folder called `sources` in the `scala` module.
Each project uses it by defining the following properties:
```xml
<sourceDirectory>../sources/src/main/scala</sourceDirectory>
<resources>
    <resource>
        <directory>../sources/src/main/resources</directory>
    </resource>
</resources>
<testSourceDirectory>../sources/src/test/scala</testSourceDirectory>
<testResources>
    <testResource>
        <directory>../sources/src/test/resources</directory>
    </testResource>
</testResources>
```

**Note:** when using your favorite IDE, you might have to "close" or "unload" 2 of the 3 projects.
Some IDE are not able to handle shared sources because a source path can be attached to a single IDE project.
If so, only loading the latest (`scala_2.13` project) is recommended.

## Language traits generation

The language traits (`io.cucumber.scala.EN` for instance) are generated automatically using
a Groovy script at compile time.

See in `sources/src/main/groovy/` folder.
