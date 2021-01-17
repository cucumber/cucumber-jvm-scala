package io.cucumber.scala

import java.lang.reflect.{ParameterizedType, Type}

import izumi.reflect.macrortti._

object ScalaTypeHelper {

  def asJavaType(t: LightTypeTag): Type = {
    if (t.typeArgs.isEmpty) {
      classFromLTT(t)
    } else {
      new ScalaParameterizedType(t)
    }
  }

  def classFromLTT(t: LightTypeTag): Class[_] = {
    Class.forName(classNameFromLTT(t))
  }

  // TOOD add tests on this one!
  def classNameFromLTT(t: LightTypeTag): String = {
    // my.company.OuterClass::my.company.OuterClass.InnerClass[=TypeParam]
    // my.company.OuterObject$::my.company.OuterObject$.InnerClass[=TypeParam]
    val repr = t.repr.split("\\[").head.split("::")
    if (repr.length == 1) {
      val k = repr.head
      // FIXME
      if (k == "scala.Int") {
        "java.lang.Integer"
      } else if (k == "scala.Byte") {
        "java.lang.Byte"
      } else if (k == "scala.Short") {
        "java.lang.Short"
      } else if (k == "scala.Long") {
        "java.lang.Long"
      } else if (k == "scala.Float") {
        "java.lang.Float"
      } else if (k == "scala.Double") {
        "java.lang.Double"
      } else if (k == "scala.Boolean") {
        "java.lang.Boolean"
      } else {
        k
      }
    } else if (repr.length == 2) {
      if (repr.head.endsWith("$")) {
        repr.head + repr(1).substring(repr.head.length + 1)
      } else {
        repr.head + "$" + repr(1).substring(repr.head.length + 1)
      }
    } else {
      throw new IllegalArgumentException(
        s"Unable to understand the type ${t.repr}, please open an issue at https://github.com/cucumber/cucumber-jvm-scala"
      )
    }
  }

}

class ScalaParameterizedType(t: LightTypeTag) extends ParameterizedType {

  private val typeArgs = t.typeArgs.map(ScalaTypeHelper.asJavaType).toArray
  private val rawType = ScalaTypeHelper.classFromLTT(t)

  override def getActualTypeArguments: Array[Type] = typeArgs

  override def getRawType: Type = rawType

  override def getOwnerType: Type = null

}
