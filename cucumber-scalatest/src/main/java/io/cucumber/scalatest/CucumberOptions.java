package io.cucumber.scalatest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Configuration annotation for Cucumber tests in ScalaTest.
 *
 * Use this annotation to configure Cucumber options for your test suite.
 *
 * Example:
 * <pre>
 * &#64;CucumberOptions(
 *   glue = {"com.example.stepdefinitions"},
 *   features = {"classpath:features"},
 *   plugin = {"pretty"}
 * )
 * class RunCucumberTest extends CucumberSuite
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CucumberOptions {

    /**
     * @return the package(s) containing the glue code (step definitions and hooks).
     */
    String[] glue() default {};

    /**
     * @return the paths to the feature file(s) or directory(ies) on the classpath.
     */
    String[] features() default {};

    /**
     * @return output plugins to use
     */
    String[] plugin() default {};

    /**
     * @return only run scenarios tagged with tags matching this expression
     */
    String tags() default "";

    /**
     * @return true if glue code should be executed in dry run mode
     */
    boolean dryRun() default false;

    /**
     * @return true if output should not use ANSI color escape codes
     */
    boolean monochrome() default false;

    /**
     * @return only run scenarios matching this name
     */
    String[] name() default {};

    /**
     * @return snippets should use this snippet type
     */
    String snippets() default "UNDERSCORE";

    /**
     * @return the object factory class
     */
    Class<?> objectFactory() default Object.class;

    /**
     * @return true if results should be published
     */
    boolean publish() default false;
}
