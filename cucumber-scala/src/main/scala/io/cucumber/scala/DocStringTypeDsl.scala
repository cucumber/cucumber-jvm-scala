package io.cucumber.scala

import io.cucumber.scala.Aliases.DocStringDefinitionBody

import scala.reflect.ClassTag

private[scala] trait DocStringTypeDsl extends BaseScalaDsl {

  /** Register doc string type.
    *
    * @param contentType Name of the content type.
    * @param body        a function that creates an instance of <code>T</code>
    *                    from the doc string
    * @tparam T type to convert to
    */
  def DocStringType[T](
      contentType: String
  )(body: DocStringDefinitionBody[T])(implicit ev: ClassTag[T]): Unit = {
    registry.registerDocStringType(
      ScalaDocStringTypeDetails[T](contentType, body, ev)
    )
  }

}
