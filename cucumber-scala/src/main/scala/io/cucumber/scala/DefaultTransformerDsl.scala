package io.cucumber.scala

import io.cucumber.scala.Aliases.{
  DefaultDataTableCellTransformerBody,
  DefaultDataTableEntryTransformerBody,
  DefaultParameterTransformerBody
}

private[scala] trait DefaultTransformerDsl extends BaseScalaDsl {

  /** Register default parameter type transformer.
    *
    * @param body
    *   converts `String` argument to an instance of the `Type` argument
    */
  def DefaultParameterTransformer(
      body: DefaultParameterTransformerBody
  ): Unit = {
    registry.registerDefaultParameterTransformer(
      ScalaDefaultParameterTransformerDetails(body)
    )
  }

  /** Register default data table cell transformer.
    *
    * @param body
    *   converts `String` argument to an instance of the `Type` argument
    */
  def DefaultDataTableCellTransformer(
      body: DefaultDataTableCellTransformerBody
  ): Unit = {
    DefaultDataTableCellTransformer(NO_REPLACEMENT)(body)
  }

  /** Register default data table cell transformer with a replacement. <p> A
    * data table can only represent absent and non-empty strings. By replacing a
    * known value (for example [empty]) a data table can also represent empty
    * strings. *
    *
    * @param replaceWithEmptyString
    *   a string that will be replaced with an empty string.
    * @param body
    *   converts `String` argument to an instance of the `Type` argument
    */
  def DefaultDataTableCellTransformer(
      replaceWithEmptyString: String
  )(body: DefaultDataTableCellTransformerBody): Unit = {
    DefaultDataTableCellTransformer(Seq(replaceWithEmptyString))(body)
  }

  private def DefaultDataTableCellTransformer(
      replaceWithEmptyString: Seq[String]
  )(body: DefaultDataTableCellTransformerBody): Unit = {
    registry.registerDefaultDataTableCellTransformer(
      ScalaDefaultDataTableCellTransformerDetails(replaceWithEmptyString, body)
    )
  }

  /** Register default data table entry transformer.
    *
    * @param body
    *   converts `Map[String,String]` argument to an instance of the `Type`
    *   argument
    */
  def DefaultDataTableEntryTransformer(
      body: DefaultDataTableEntryTransformerBody
  ): Unit = {
    DefaultDataTableEntryTransformer(NO_REPLACEMENT)(body)
  }

  /** Register default data table cell transformer with a replacement. <p> A
    * data table can only represent absent and non-empty strings. By replacing a
    * known value (for example [empty]) a data table can also represent empty
    * strings.
    *
    * @param replaceWithEmptyString
    *   a string that will be replaced with an empty string.
    * @param body
    *   converts `Map[String,String]` argument to an instance of the `Type`
    *   argument
    */
  def DefaultDataTableEntryTransformer(
      replaceWithEmptyString: String
  )(body: DefaultDataTableEntryTransformerBody): Unit = {
    DefaultDataTableEntryTransformer(Seq(replaceWithEmptyString))(body)
  }

  private def DefaultDataTableEntryTransformer(
      replaceWithEmptyString: Seq[String]
  )(body: DefaultDataTableEntryTransformerBody): Unit = {
    registry.registerDefaultDataTableEntryTransformer(
      ScalaDefaultDataTableEntryTransformerDetails(replaceWithEmptyString, body)
    )
  }

}
