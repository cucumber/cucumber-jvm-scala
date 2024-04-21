package io.cucumber.core.options

import io.cucumber.core.exception.CucumberException
import io.cucumber.core.feature.{FeatureWithLines, GluePath}
import io.cucumber.core.resource.ClasspathSupport.CLASSPATH_SCHEME_PREFIX
import io.cucumber.core.snippets.SnippetType
import io.cucumber.scalatest.CucumberSuiteOptions
import io.cucumber.tagexpressions.{TagExpressionException, TagExpressionParser}

import java.util.regex.Pattern
import scala.jdk.OptionConverters.RichOptional
import scala.util.{Failure, Success, Try}

object CucumberSuiteOptionsParser {

  def unsafeParse[T <: CucumberSuiteOptions](
      options: T
  ): RuntimeOptionsBuilder = {
    parse(options) match {
      case Success(value) => value
      case Failure(exception) =>
        println(
          "An exception happened while parsing CucumberSuite options. This is likely an issue with Cucumber-Scalatest implementation. Please open an issue on GitHub."
        )
        exception.printStackTrace()
        throw exception
    }
  }

  def parse[T <: CucumberSuiteOptions](
      options: T
  ): Try[RuntimeOptionsBuilder] = {
    Try {
      // TODO verify how getClass behaves with the trait
      val clazz = options.getClass

      println("aaa")

      val args = new RuntimeOptionsBuilder

      println("bbb")

      if (options.dryRun) {
        args.setDryRun(true)
      }

      println("bbb2")

      if (options.monochrome) {
        args.setMonochrome(true)
      }

      println("bbb3")

      val tagExpression = options.matchingTags
      if (tagExpression.nonEmpty) {
        Try {
          args.addTagFilter(TagExpressionParser.parse(tagExpression))
          println("ok tags")
        }.recover { case tee: TagExpressionException =>
          println("Invalid tag expression a")
          throw new IllegalArgumentException(
            String.format(
              "Invalid tag expression at '%s'",
              clazz.getName
            ),
            tee
          )
        }
      }

      println("ccc")

      options.plugins.foreach(args.addPluginName)

      if (options.publish) {
        args.setPublish(true)
      }

      options.names.foreach(name => args.addNameFilter(Pattern.compile(name)))

      val snippetType = options.snippets match {
        case CucumberSuiteOptions.SnippetType.UNDERSCORE =>
          SnippetType.UNDERSCORE
        case CucumberSuiteOptions.SnippetType.CAMELCASE => SnippetType.CAMELCASE
      }
      args.setSnippetType(snippetType)

      val hasExtraGlue = options.extraGluePackages.nonEmpty
      val hasGlue = options.gluePackages.nonEmpty

      println("glue")

      if (hasExtraGlue && hasGlue) {
        throw new CucumberException(
          "gluePackages and extraGluePackages cannot be specified at the same time"
        )
      }

      if (hasExtraGlue) {
        options.extraGluePackages.foreach(glue =>
          args.addGlue(GluePath.parse(glue))
        )
      }

      if (hasGlue) {
        options.gluePackages.foreach(glue => args.addGlue(GluePath.parse(glue)))
      } else {
        args.addGlue(GluePath.parse(packageName(clazz)))
      }

      if (options.featuresPath.nonEmpty) {
        options.featuresPath.foreach { feature =>
          val parsed = FeatureWithLinesOrRerunPath.parse(feature)
          parsed.getFeaturesToRerun.toScala.foreach(features =>
            args.addRerun(features)
          )
          parsed.getFeatureWithLines.toScala.foreach(features =>
            args.addFeature(features)
          )
        }
      } else {
        val packageName = packagePath(clazz)
        val featureWithLines = FeatureWithLines.parse(packageName)
        args.addFeature(featureWithLines)
      }

      // TODO
//      args.setObjectFactoryClass(options.objectFactory)
//      args.setUuidGeneratorClass(options.uuidGenerator)

      println("args")

      args
    }
  }

  private def packagePath(clazz: Class[_]): String = {
    val name = packageName(clazz)
    if (name.isEmpty) return CLASSPATH_SCHEME_PREFIX + "/"
    CLASSPATH_SCHEME_PREFIX + "/" + name.replace('.', '/')
  }

  private def packageName(clazz: Class[_]): String = {
    val className = clazz.getName
    className.substring(0, Math.max(0, className.lastIndexOf('.')))
  }

}
