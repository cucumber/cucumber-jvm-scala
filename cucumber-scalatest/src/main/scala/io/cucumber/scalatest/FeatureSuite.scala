package io.cucumber.scalatest

import io.cucumber.core.filter.Filters
import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.runtime.CucumberExecutionContext
import org.scalatest.{Args, Status, Suite}

import scala.jdk.CollectionConverters._
import scala.collection.immutable.IndexedSeq

/** A feature file, containing one nested [[ScenarioSuite]] per selected
  * scenario.
  */
private[scalatest] final class FeatureSuite(
    feature: Feature,
    filters: Filters,
    context: CucumberExecutionContext
) extends Suite {

  val pickles: List[Pickle] =
    feature.getPickles.asScala.filter(filters.test).toList

  override def suiteName: String = {
    val name = feature.getName.orElse("")
    if (name.nonEmpty) name else feature.getUri.toString
  }

  override def suiteId: String = feature.getUri.toString

  override def testNames: Set[String] = Set.empty

  override def nestedSuites: IndexedSeq[Suite] =
    pickles.map(new ScenarioSuite(feature, _, context)).toIndexedSeq

  override def run(testName: Option[String], args: Args): Status = {
    context.beforeFeature(feature)
    super.run(None, args)
  }

  override protected def runNestedSuites(args: Args): Status =
    NestedSuites.runSequentially(this, args, level = 1)
}
