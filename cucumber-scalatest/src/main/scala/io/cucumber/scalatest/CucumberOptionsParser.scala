package io.cucumber.scalatest

import io.cucumber.core.feature.{FeatureWithLines, GluePath}
import io.cucumber.core.options.RuntimeOptionsBuilder
import io.cucumber.core.resource.ClasspathSupport.CLASSPATH_SCHEME_PREFIX
import io.cucumber.tagexpressions.{TagExpressionException, TagExpressionParser}

import scala.util.{Failure, Try}

// Note: this is inspired by CucumberOptionsAnnotationParser in cucumber-core
object CucumberOptionsParser {

  def parse(
      options: CucumberOptions,
      clazz: Class[_]
  ): RuntimeOptionsBuilder = {

    val builder = new RuntimeOptionsBuilder()

    if (options.features.nonEmpty) {
      options.features.foreach { feature =>
        builder.addFeature(FeatureWithLines.parse(feature))
      }
    } else {
      // Add default feature path if no feature path is specified
      val packageName = packagePath(clazz)
      builder.addFeature(FeatureWithLines.parse(packageName))
    }

    if (options.glue.nonEmpty) {
      options.glue.foreach { glue =>
        builder.addGlue(GluePath.parse(glue))
      }
    } else {
      // Add default glue path if no overriding glue is specified
      builder.addGlue(GluePath.parse(clazz.getPackage.getName))
    }

    options.plugin.foreach { plugin =>
      builder.addPluginName(plugin)
    }

    options.tags.filter(_.nonEmpty) match {
      case Some(tagExpression) =>
        Try {
          builder.addTagFilter(TagExpressionParser.parse(tagExpression))
        }.recoverWith { case tee: TagExpressionException =>
          Failure(
            new IllegalArgumentException(
              s"Invalid tag expression at '${clazz.getName}'",
              tee
            )
          )
        }.get // TODO: see if we can make it more Scala-esque without throwing :)
      case None =>
      // Nothing to do
    }

    builder
  }

  private def packagePath(clazz: Class[_]): String = {
    val packageName = clazz.getPackage.getName
    if (packageName.isEmpty) {
      CLASSPATH_SCHEME_PREFIX + "/"
    } else {
      CLASSPATH_SCHEME_PREFIX + "/" + packageName.replace('.', '/')
    }
  }

}
