package io.cucumber.scalatest

import io.cucumber.core.exception.CucumberException
import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.runtime.CucumberExecutionContext
import org.scalatest.{Args, Status, Suite}

import java.util.function.Predicate
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.jdk.OptionConverters.RichOptional
import scala.util.Try

private[scalatest] final class FeatureSuite(
    private val feature: Feature,
    private val uniqueSuffix: Option[Int],
    private val filter: Predicate[Pickle],
    private val context: CucumberExecutionContext
) extends Suite {

  private val children: Seq[PickleSuite] = {
    val groupedByName: Map[String, Seq[Pickle]] =
      feature.getPickles.asScala.toSeq.groupBy(_.getName)
    feature.getPickles.asScala.toSeq
      .filter(p => filter.test(p))
      .map { (pickle: Pickle) =>
        val featureName = suiteName
        val exampleId = FilenameCompatibleNames.uniqueSuffix(
          groupedByName,
          pickle,
          (p: Pickle) => p.getName
        )
        PickleSuite.withNoStepDescriptions(
          featureName,
          context,
          pickle,
          exampleId
        )
      }
  }

  override def suiteName: String = {
    val name = feature.getName.toScala.getOrElse("EMPTY_NAME")
    FilenameCompatibleNames.createName(name, uniqueSuffix, false)
  }

  def isEmpty: Boolean = children.isEmpty

  override protected def runNestedSuites(args: Args): Status = {
    context.beforeFeature(feature)
    super.runNestedSuites(args)
  }

  override def nestedSuites: IndexedSeq[Suite] = children.toIndexedSeq

}

object FeatureSuite {

  def createUnsafe(
      feature: Feature,
      uniqueSuffix: Option[Int],
      filter: Predicate[Pickle],
      context: CucumberExecutionContext
  ): FeatureSuite = create(feature, uniqueSuffix, filter, context).get

  def create(
      feature: Feature,
      uniqueSuffix: Option[Int],
      filter: Predicate[Pickle],
      context: CucumberExecutionContext
  ): Try[FeatureSuite] = {
    Try {
      new FeatureSuite(feature, uniqueSuffix, filter, context)
    }.recover { case e: Throwable =>
      throw new CucumberException("Failed to create scenario runner", e)
    }
  }

}
