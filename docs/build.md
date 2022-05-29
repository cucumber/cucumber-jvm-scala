# Build

To compile and test the whole project you can run the following command:

```shell
$ sbt clean compile test
```

## Project structure

The project contains several subprojects:
- `cucumber-scala`: contains the codebase of the Cucumber Scala implementation
- `integration-tests`: contains integration tests projects
  - `common`: general integration tests
  - `jackson`: Jackson integration specific tests
  - `picocontainer`: Picocontainer integration specific tests
- `examples`: contains a sample project

Each of these subproject is also derived for each target Scala version. See below.

## Cross compilation

Cross compilation to multiple Scala versions is handled by the [sbt-projectmatrix](https://github.com/sbt/sbt-projectmatrix) plugin.

The target versions are defined in the `build.sbt` on each sub-project:
```scala
project
  ...
  .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))
```

A sbt sub-project is generated for each targeted Scala version with the version as a suffix
(with Scala 2.13 being the current default version):

```shell
$ sbt projects
...
[info]     cucumberScala
[info]     cucumberScala2_12
[info]     cucumberScala3
[info]     examples
[info]     examples2_12
[info]     examples3
[info]   * root
```

### Sources

Sources should most of the time be compatible for all target Scala versions in order to ease maintenance.

However, if needed, it's possible to define different sources for each Scala version.

These version-specific sources should be put in a directory called `src/main/scala-<version>` and the `build.sbt`
should declare them as additional sources directories like:
```scala
Compile / unmanagedSourceDirectories ++= {
  val sourceDir = (Compile / sourceDirectory).value
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) =>
      Seq(sourceDir / "scala-2")
    case Some((3, 0)) =>
      Seq(sourceDir / "scala-3")
    case _ =>
      Seq()
  }
}
```

The same can be done for tests.

## Language traits generation

The language traits (`io.cucumber.scala.EN` for instance) are generated automatically at compile time.

The `project` meta project defines a `I18nGenerator` object responsible for generating their content.

At compilation, this generated content is injected as source files thanks to a sbt source generator:
```scala
// Generate I18n traits
Compile / sourceGenerators += Def.task {
  val file =
    (Compile / sourceManaged).value / "io/cucumber/scala" / "I18n.scala"
  IO.write(file, I18nGenerator.i18n)
  Seq(file)
}.taskValue
```
