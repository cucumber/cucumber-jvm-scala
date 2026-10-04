package io.cucumber.scalatest

/** Programmatic Cucumber configuration for a [[CucumberSuite]].
  *
  * Every value is optional: anything left empty falls back on
  * `cucumber.properties`, and `CUCUMBER_*` environment variables and
  * `cucumber.*` system properties always take precedence over what is declared
  * here.
  *
  * @param features
  *   feature paths, e.g. `classpath:features` or `src/test/resources/x.feature`
  *   (default: the classpath folder matching the suite's package)
  * @param glue
  *   packages containing step definitions and hooks (default: the suite's
  *   package)
  * @param plugin
  *   Cucumber plugins, e.g. `pretty` or `json:target/cucumber.json`
  * @param tags
  *   tag expression selecting the scenarios to run, e.g. `@smoke and not @wip`
  * @param properties
  *   any other Cucumber configuration property, e.g.
  *   `Map("cucumber.object-factory" -> "io.cucumber.picocontainer.PicoFactory")`
  */
final case class CucumberOptions(
    features: Seq[String] = Seq.empty,
    glue: Seq[String] = Seq.empty,
    plugin: Seq[String] = Seq.empty,
    tags: Option[String] = None,
    properties: Map[String, String] = Map.empty
)
