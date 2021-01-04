package io.cucumber.scala

import java.lang.reflect.{ParameterizedType, Type}

object ScalaTypeHelper {

  def asJavaType(m: MyType): Type = {
    if (m.args.isEmpty) {
      Class.forName(m.me)
    } else {
      new ScalaParameterizedType(m)
    }
  }

}

class ScalaParameterizedType(myType: MyType) extends ParameterizedType {

  private val typeArgs = myType.args.map(ScalaTypeHelper.asJavaType).toArray
  private val rawType = Class.forName(myType.me)

  override def getActualTypeArguments: Array[Type] = typeArgs

  override def getRawType: Type = rawType

  override def getOwnerType: Type = null

}
