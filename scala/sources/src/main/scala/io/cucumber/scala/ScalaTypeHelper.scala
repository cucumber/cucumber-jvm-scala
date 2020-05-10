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

  override def getActualTypeArguments: Array[Type] = manifest.typeArguments.map(ScalaTypeHelper.asJavaType).toArray

  override def getRawType: Type = manifest.runtimeClass

  override def getOwnerType: Type = null

}
