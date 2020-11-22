package io.cucumber.scala

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/** <p>This trait register a `DefaultDataTableEntryTransformer` using Jackson `ObjectMapper`.</p>
  *
  * <p>The `[empty]` string is used as default empty string replacement. You can override it if you need to.</p>
  *
  * <p>Note: Jackson is not included with Cucumber Scala, you have to add the dependency:
  * `com.fasterxml.jackson.module:jackson-module-scala_2.xx`
  * to your project if you want to use this trait.</p>
  */
trait JacksonDefaultDataTableEntryTransformer extends ScalaDsl {

  /** Define the string to be used as replacement for empty.
    * Default is `[empty]`.
    */
  def emptyStringReplacement: String = "[empty]"

  /** Create the Jackson ObjectMapper to be used.
    * Default is a simple ObjectMapper with DefaultScalaModule registered.
    */
  def createObjectMapper(): ObjectMapper = {
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
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
