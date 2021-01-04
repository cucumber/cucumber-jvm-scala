package io.cucumber.scala

import java.lang.reflect.{ParameterizedType, Type}

object ScalaTypeHelper {

  def asJavaType(m: Manifest[_]): Type = {
    if (m.typeArguments.isEmpty) {
      m.runtimeClass
    } else {
      new ScalaParameterizedType(m)
    }
  }

}

class ScalaParameterizedType(manifest: Manifest[_]) extends ParameterizedType {

  private val typeArgs =
    manifest.typeArguments.map(ScalaTypeHelper.asJavaType).toArray
  private val rawType = manifest.runtimeClass

  override def getActualTypeArguments: Array[Type] = typeArgs

  override def getRawType: Type = rawType

  override def getOwnerType: Type = null

}
