package io.cucumber.scala

import java.lang.reflect.{
  ParameterizedType => JavaParameterizedType,
  Type => JavaType
}

import io.cucumber.core.exception.CucumberException

import scala.reflect.runtime.universe.{Mirror, Type, TypeTag}
import scala.util.{Failure, Success, Try}

object ScalaTypeHelper {

  def asJavaType(m: TypeTag[_]): JavaType = {
    val mirror: Mirror = m.mirror

    def _asJavaTypeRec(t: Type): JavaType = {
      val clazz: Class[_] = mirror.runtimeClass(t.typeSymbol.asClass)
      t.typeArgs match {
        case Nil =>
          clazz
        case args =>
          val typeArgs = args.map(_asJavaTypeRec)
          new ScalaParameterizedType(clazz, typeArgs)
      }
    }

    Try(_asJavaTypeRec(m.tpe)) match {
      case Success(javaType) =>
        javaType
      case Failure(ex) =>
        throw new CucumberException(
          "Internal error in Cucumber Scala, please open an issue at https://github.com/cucumber/cucumber-jvm-scala",
          ex
        )
    }
  }

}

class ScalaParameterizedType(rawType: JavaType, typeArgs: List[JavaType])
    extends JavaParameterizedType {

  override def getActualTypeArguments: Array[JavaType] = typeArgs.toArray

  override def getRawType: JavaType = rawType

  override def getOwnerType: JavaType = null

}
