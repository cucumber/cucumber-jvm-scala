package tests.misc

import java.util.Optional

import io.cucumber.scala.{EN, ScalaDsl}

class OptionalCaptureGroupsSteps extends ScalaDsl with EN {

  // Scala 2.13 only
  // import scala.jdk.OptionConverters._

  import OptionalCaptureGroupsSteps._

  Given("""^I have the name:\s?(.+)?$""") { (name: Optional[String]) =>
    val option = name.toScala
    assert(option.isDefined)
    assert(option.getOrElse("Nope") == "Jack")
  }

  Given("""^I don't have the name:\s?(.+)?$""") { (name: Optional[String]) =>
    val option = name.toScala
    assert(option.isEmpty)
  }

}

object OptionalCaptureGroupsSteps {

  implicit class RichOptional[A](private val o: java.util.Optional[A]) extends AnyVal {

    def toScala: Option[A] = if (o.isPresent) Some(o.get) else None

  }

}