package io.cucumber.scalatest

import io.cucumber.core.gherkin.{Feature, Pickle}
import io.cucumber.core.runtime.CucumberExecutionContext
import org.scalatest.{Args, Status, Suite}

import java.util.function.Predicate
import scala.jdk.CollectionConverters._

private class FeatureSuite(
    feature: Feature,
    context: CucumberExecutionContext,
    filters: Predicate[Pickle]
) extends Suite {

  private lazy val children: scala.collection.immutable.IndexedSeq[PickleSuite] = {
    // TODO check if need suffix
    feature
      .getPickles()
      .asScala
      .filter(filters.test)
      .map(pickle => new PickleSuite(pickle, context))
      .toIndexedSeq
  }

  override def suiteName: String =
    feature.getName.orElse("EMPTY_NAME") // TODO maybe handle uniqueSuffix for duplicated feature names

  override def nestedSuites: scala.collection.immutable.IndexedSeq[Suite] = children

  override def run(testName: Option[String], args: Args): Status = {
    context.beforeFeature(feature)
    val status = super.run(testName, args)
    println("Feature Suite " + feature.getName + " " + status.succeeds())
    status
  }

  override def runNestedSuites(args: Args): Status = {
    val status = super.runNestedSuites(args)
    println("Feature nested suites " + status.succeeds())
    status
  }

  def isEmpty(): Boolean = children.isEmpty

}
