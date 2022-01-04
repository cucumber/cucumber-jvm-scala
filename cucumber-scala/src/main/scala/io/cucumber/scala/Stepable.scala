package io.cucumber.scala

import java.lang.reflect.{
  ParameterizedType => JavaParameterizedType,
  Type => JavaType
}
import scala.reflect.ClassTag

/** Indicates that a type `T` can be used in step definitions
  */
trait Stepable[T] {
  def asJavaType: JavaType
}

object Stepable {

  implicit def stepable9[T[
      _,
      _,
      _,
      _,
      _,
      _,
      _,
      _,
      _
  ], X1: Stepable, X2: Stepable, X3: Stepable, X4: Stepable, X5: Stepable, X6: Stepable, X7: Stepable, X8: Stepable, X9: Stepable](
      implicit ct: ClassTag[T[X1, X2, X3, X4, X5, X6, X7, X8, X9]]
  ): Stepable[T[X1, X2, X3, X4, X5, X6, X7, X8, X9]] =
    new Stepable[T[X1, X2, X3, X4, X5, X6, X7, X8, X9]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType,
            implicitly[Stepable[X4]].asJavaType,
            implicitly[Stepable[X5]].asJavaType,
            implicitly[Stepable[X6]].asJavaType,
            implicitly[Stepable[X7]].asJavaType,
            implicitly[Stepable[X8]].asJavaType,
            implicitly[Stepable[X9]].asJavaType
          )
        )
    }

  implicit def stepable8[T[
      _,
      _,
      _,
      _,
      _,
      _,
      _,
      _
  ], X1: Stepable, X2: Stepable, X3: Stepable, X4: Stepable, X5: Stepable, X6: Stepable, X7: Stepable, X8: Stepable](
      implicit ct: ClassTag[T[X1, X2, X3, X4, X5, X6, X7, X8]]
  ): Stepable[T[X1, X2, X3, X4, X5, X6, X7, X8]] =
    new Stepable[T[X1, X2, X3, X4, X5, X6, X7, X8]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType,
            implicitly[Stepable[X4]].asJavaType,
            implicitly[Stepable[X5]].asJavaType,
            implicitly[Stepable[X6]].asJavaType,
            implicitly[Stepable[X7]].asJavaType,
            implicitly[Stepable[X8]].asJavaType
          )
        )
    }

  implicit def stepable7[T[
      _,
      _,
      _,
      _,
      _,
      _,
      _
  ], X1: Stepable, X2: Stepable, X3: Stepable, X4: Stepable, X5: Stepable, X6: Stepable, X7: Stepable](
      implicit ct: ClassTag[T[X1, X2, X3, X4, X5, X6, X7]]
  ): Stepable[T[X1, X2, X3, X4, X5, X6, X7]] =
    new Stepable[T[X1, X2, X3, X4, X5, X6, X7]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType,
            implicitly[Stepable[X4]].asJavaType,
            implicitly[Stepable[X5]].asJavaType,
            implicitly[Stepable[X6]].asJavaType,
            implicitly[Stepable[X7]].asJavaType
          )
        )
    }

  implicit def stepable6[T[
      _,
      _,
      _,
      _,
      _,
      _
  ], X1: Stepable, X2: Stepable, X3: Stepable, X4: Stepable, X5: Stepable, X6: Stepable](
      implicit ct: ClassTag[T[X1, X2, X3, X4, X5, X6]]
  ): Stepable[T[X1, X2, X3, X4, X5, X6]] =
    new Stepable[T[X1, X2, X3, X4, X5, X6]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType,
            implicitly[Stepable[X4]].asJavaType,
            implicitly[Stepable[X5]].asJavaType,
            implicitly[Stepable[X6]].asJavaType
          )
        )
    }

  implicit def stepable5[T[
      _,
      _,
      _,
      _,
      _
  ], X1: Stepable, X2: Stepable, X3: Stepable, X4: Stepable, X5: Stepable](
      implicit ct: ClassTag[T[X1, X2, X3, X4, X5]]
  ): Stepable[T[X1, X2, X3, X4, X5]] =
    new Stepable[T[X1, X2, X3, X4, X5]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType,
            implicitly[Stepable[X4]].asJavaType,
            implicitly[Stepable[X5]].asJavaType
          )
        )
    }

  implicit def stepable4[T[
      _,
      _,
      _,
      _
  ], X1: Stepable, X2: Stepable, X3: Stepable, X4: Stepable](implicit
      ct: ClassTag[T[X1, X2, X3, X4]]
  ): Stepable[T[X1, X2, X3, X4]] =
    new Stepable[T[X1, X2, X3, X4]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType,
            implicitly[Stepable[X4]].asJavaType
          )
        )
    }

  implicit def stepable3[T[_, _, _], X1: Stepable, X2: Stepable, X3: Stepable](
      implicit ct: ClassTag[T[X1, X2, X3]]
  ): Stepable[T[X1, X2, X3]] =
    new Stepable[T[X1, X2, X3]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType,
            implicitly[Stepable[X3]].asJavaType
          )
        )
    }

  implicit def stepable2[T[_, _], X1: Stepable, X2: Stepable](implicit
      ct: ClassTag[T[X1, X2]]
  ): Stepable[T[X1, X2]] =
    new Stepable[T[X1, X2]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType,
            implicitly[Stepable[X2]].asJavaType
          )
        )
    }

  implicit def stepable1[T[_], X1: Stepable](implicit
      ct: ClassTag[T[X1]]
  ): Stepable[T[X1]] =
    new Stepable[T[X1]] {
      def asJavaType: JavaType =
        new ScalaParameterizedType(
          ct.runtimeClass,
          Array(
            implicitly[Stepable[X1]].asJavaType
          )
        )
    }

  implicit def stepable0[T: ClassTag]: Stepable[T] =
    new Stepable[T] {
      def asJavaType: JavaType = implicitly[ClassTag[T]].runtimeClass
    }

}

class ScalaParameterizedType(val self: JavaType, val args: Array[JavaType])
    extends JavaParameterizedType {

  override def getActualTypeArguments: Array[JavaType] = args

  override def getRawType: JavaType = self

  override def getOwnerType: JavaType = null

  override def getTypeName: String =
    self.getTypeName + args.map(_.getTypeName).mkString("[", ",", "]")

  override def equals(obj: Any): Boolean = obj match {
    case x: ScalaParameterizedType =>
      x.self == self && (x.args sameElements args)
    case _ => false
  }

}
