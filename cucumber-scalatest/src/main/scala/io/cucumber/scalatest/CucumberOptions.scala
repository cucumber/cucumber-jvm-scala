package io.cucumber.scalatest

/** Configuration for Cucumber tests.
  *
  * <p>
  * <strong><u>Features</u></strong>
  * <p>
  * A list of features paths
  * <p> A feature path is constructed as
  * {@code [ PATH[.feature[:LINE]*] | URI[.feature[:LINE]*] | @PATH ]} <p>
  * Examples:
  * <ul>
  *   <li>{@code src/test/resources/features} -- All features in the {@code src/test/resources/features} directory</li>
  *   <li>{@code classpath:com/example/application} -- All features in the {@code com.example.application} package</li>
  *   <li>{@code in-memory:/features} -- All features in the {@code /features} directory on an in memory file system
  *   supported by {@link java.nio.file.FileSystems}</li>
  *   <li>{@code src/test/resources/features/example.feature:42} -- The scenario or example at line 42 in the example
  *   feature file</li>
  * </ul>
  * <p>
  * When no feature path is provided, Cucumber will use the package of the suite class.
  * For example, if the suite class is {@code com.example.RunCucumber} then features are assumed to be located in
  * {@code classpath:com/example}.
  *
  * <p>
  * <strong><u>Glue</u></strong>
  * <p>
  * Package to load glue code (step definitions, hooks and plugins) from. E.g: {@code com.example.app}
  * <p>
  * When no glue is provided, Cucumber will use the package of the suite class.
  * For example, if the suite class is {@code com.example.RunCucumber} then glue is assumed to be located in {@code com.example}.
  *
  * <p>
  * <strong><u>Plugins</u></strong>
  * <p>
  * Register plugins. Built-in plugin types: {@code junit}, {@code html}, {@code pretty}, {@code progress}, {@code json},
  * {@code usage}, {@code unused}, {@code rerun}, {@code testng}.
  * <p>
  * Can also be a fully qualified class name, allowing registration of 3rd party plugins.
  * <p>
  * Plugins can be provided with an argument. For example {@code json:target/cucumber-report.json}
  *
  * <p>
  * <strong><u>Tags</u></strong>
  * <p>
  * Only run scenarios tagged with tags matching <a href="https://github.com/cucumber/tag-expressions">Tag Expression</a>.
  * <p>
  * For example {@code "@smoke and not @fast"}.
  *
  * @param features A list of features paths
  * @param glue Package to load glue code (step definitions, hooks and plugins) from. E.g: {@code com.example.app}
  * @param plugin Plugins
  * @param tags Tags
  */
case class CucumberOptions(
    features: List[String] = List.empty,
    glue: List[String] = List.empty,
    plugin: List[String] = List.empty,
    tags: Option[String] = None
)
