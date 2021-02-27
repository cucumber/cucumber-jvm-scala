package io.cucumber.scala

import io.github.gaeljw.typetrees.TypeTreeTag
import java.lang.reflect.{ParameterizedType, Type}

object ScalaTypeHelper {

  def asJavaType(tag: TypeTreeTag): Type = {
    if (tag.args.isEmpty) {
      tag.self.runtimeClass
    } else {
      new ScalaParameterizedType(tag)
    }
  }

}

class ScalaParameterizedType(tag: TypeTreeTag) extends ParameterizedType {

  override def getActualTypeArguments: Array[Type] =
    tag.args.map(ScalaTypeHelper.asJavaType).toArray

  override def getRawType: Type = tag.self.runtimeClass

  override def getOwnerType: Type = null

}
