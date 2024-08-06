package io.cucumber.scalatest

import io.cucumber.core.backend.ObjectFactory
import io.cucumber.core.eventbus.UuidGenerator
import io.cucumber.scalatest.CucumberSuiteOptions.SnippetType

trait CucumberSuiteOptions {

  /** @return
    *   true if glue code execution should be skipped.
    */
  def dryRun = false

  /** A list of features paths. <p> A feature path is constructed as
    * {@code [ PATH[.feature[:LINE]*] | URI[.feature[:LINE]*] | @PATH ]} <p>
    * Examples: <ul> <li>{@code src/test/resources/features} -- All features in
    * the {@code src/test/resources/features} directory</li> <li>{@code
    * classpath:com/example/application} -- All features in the
    * {@code com.example.application} package</li> <li>{@code
    * in-memory:/features} -- All features in the {@code /features} directory on
    * an in memory file system supported by {@link java.nio.file.FileSystems}
    * </li> <li>{@code src/test/resources/features/example.feature:42} -- The
    * scenario or example at line 42 in the example feature file</li> <li>{@code
    * \@target/rerun} -- All the scenarios in the files in the rerun
    * directory</li> <li>{@code @target/rerun/RunCucumber.txt} -- All the
    * scenarios in RunCucumber.txt file</li> </ul> <p> When no feature path is
    * provided, Cucumber will use the package of the annotated class. For
    * example, if the annotated class is {@code com.example.RunCucumber} then
    * features are assumed to be located in {@code classpath:com/example} .
    *
    * @return
    *   list of files or directories
    * @see
    *   io.cucumber.core.feature.FeatureWithLines
    */
  def featuresPath: Seq[String] = Nil

  /** Package to load glue code (step definitions, hooks and plugins) from. E.g:
    * {@code com.example.app} <p> When no glue is provided, Cucumber will use
    * the package of the annotated class. For example, if the annotated class is
    * {@code com.example.RunCucumber} then glue is assumed to be located in
    * {@code com.example} .
    *
    * @return
    *   list of package names
    * @see
    *   io.cucumber.core.feature.GluePath
    */
  def gluePackages: Seq[String] = Nil

  /** Package to load additional glue code (step definitions, hooks and plugins)
    * from. E.g: {@code com.example.app} <p> These packages are used in addition
    * to the default described in {@code #glue} .
    *
    * @return
    *   list of package names
    */
  def extraGluePackages: Seq[String] = Nil

  /** Only run scenarios tagged with tags matching <a
    * href="https://github.com/cucumber/tag-expressions">Tag Expression</a>. <p>
    * For example {@code "@smoke and not @fast"} .
    *
    * @return
    *   a tag expression
    */
  def matchingTags = ""

  /** Register plugins. Built-in plugin types: {@code junit} , {@code html} ,
    * {@code pretty} , {@code progress} , {@code json} , {@code usage} ,
    * {@code unused} , {@code rerun} , {@code testng} . <p> Can also be a fully
    * qualified class name, allowing registration of 3rd party plugins. <p>
    * Plugins can be provided with an argument. For example
    * {@code json:target/cucumber-report.json}
    *
    * @return
    *   list of plugins
    * @see
    *   Plugin
    */
  def plugins: Seq[String] = Nil

  /** Publish report to https://reports.cucumber.io. <p>
    *
    * @return
    *   true if reports should be published on the web.
    */
  def publish = false

  /** @return
    *   true if terminal output should be without colours.
    */
  def monochrome: Boolean = false

  /** Only run scenarios whose names match one of the provided regular
    * expressions.
    *
    * @return
    *   a list of regular expressions
    */
  def names: Seq[String] = Nil

  /** @return
    *   the format of the generated snippets.
    */
  def snippets: SnippetType = SnippetType.UNDERSCORE

  /** Specify a custom ObjectFactory. <p> In case a custom ObjectFactory is
    * needed, the class can be specified here. A custom ObjectFactory might be
    * needed when more granular control is needed over the dependency injection
    * mechanism.
    *
    * @return
    *   an {@link io.cucumber.core.backend.ObjectFactory} implementation
    */
  def objectFactory: Class[_ <: ObjectFactory] = classOf[NoObjectFactory]

  def uuidGenerator: Class[_ <: UuidGenerator] = classOf[NoUuidGenerator]

}

object CucumberSuiteOptions {

  sealed trait SnippetType

  object SnippetType {
    case object UNDERSCORE extends SnippetType
    case object CAMELCASE extends SnippetType
  }

}
