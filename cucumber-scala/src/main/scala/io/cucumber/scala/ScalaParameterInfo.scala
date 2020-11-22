package io.cucumber.scala

import java.lang.reflect.Type

import io.cucumber.core.backend.{ParameterInfo, TypeResolver}

class ScalaParameterInfo(typeResolver: ScalaTypeResolver)
    extends ParameterInfo {

  override def getType: Type = typeResolver.`type`

  override def isTransposed: Boolean = false

  override def getTypeResolver: TypeResolver = typeResolver

}
