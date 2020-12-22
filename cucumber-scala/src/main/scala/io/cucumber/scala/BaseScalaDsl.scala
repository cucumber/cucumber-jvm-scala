package io.cucumber.scala

private[scala] trait BaseScalaDsl {

  val NO_REPLACEMENT = Seq[String]()
  val EMPTY_TAG_EXPRESSION = ""
  val DEFAULT_BEFORE_ORDER = 1000
  val DEFAULT_AFTER_ORDER = 1000

  private[scala] val registry: ScalaDslRegistry = new ScalaDslRegistry()

}
