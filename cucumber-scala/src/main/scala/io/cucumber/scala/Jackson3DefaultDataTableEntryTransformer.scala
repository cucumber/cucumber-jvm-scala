package io.cucumber.scala

import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.scala.ScalaModule

/** <p>This trait register a `DefaultDataTableEntryTransformer` using Jackson
  * `ObjectMapper`. </p>
  *
  * <p>The `[empty]` string is used as default empty string replacement. You can
  * override it if you need to.</p>
  *
  * <p>Note: Jackson is not included with Cucumber Scala, you have to add the
  * dependency: `tools.jackson.module:jackson-module-scala` to your project if
  * you want to use this trait.</p>
  *
  * <p>For Jackson 2.x, use `JacksonDefaultDataTableEntryTransformer`
  * instead.</p>
  */
trait Jackson3DefaultDataTableEntryTransformer extends ScalaDsl {

  /** Define the string to be used as replacement for empty. Default is
    * `[empty]`.
    */
  def emptyStringReplacement: String = "[empty]"

  /** Create the Jackson ObjectMapper to be used. Default is a simple JsonMapper
    * with ScalaModule (including all builtin modules) registered.
    */
  def createObjectMapper(): ObjectMapper = {
    val scalaModule = ScalaModule
      .builder()
      .addAllBuiltinModules()
      .build()
    JsonMapper.builder().addModule(scalaModule).build()
  }

  private lazy val objectMapper: ObjectMapper = createObjectMapper()

  DefaultDataTableEntryTransformer(emptyStringReplacement) {
    (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
      objectMapper.convertValue[AnyRef](
        fromValue,
        objectMapper.constructType(toValueType)
      )
  }

}
