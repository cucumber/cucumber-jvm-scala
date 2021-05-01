package io.cucumber.scala

import io.cucumber.datatable.DataTable
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.Test

import java.lang.reflect.{ParameterizedType => JavaParameterizedType}
import java.util.{List => JavaList, Map => JavaMap}

class StepableTest {

  case class SomeCaseClass(a: String, b: Int)

  class RegularClass {}

  class GenericClass[T] {}

  @Test
  def availableImplicitForSimpleTypes(): Unit = {
    assertEquals(classOf[String], implicitly[Stepable[String]].asJavaType)
    assertEquals(classOf[Int], implicitly[Stepable[Int]].asJavaType)
    assertEquals(classOf[Long], implicitly[Stepable[Long]].asJavaType)
  }

  @Test
  def availableImplicitForSimpleTypeDatatable(): Unit = {
    assertEquals(classOf[DataTable], implicitly[Stepable[DataTable]].asJavaType)
  }

  @Test
  def availableImplicitForCaseClass(): Unit = {
    assertEquals(
      classOf[SomeCaseClass],
      implicitly[Stepable[SomeCaseClass]].asJavaType
    )
  }

  @Test
  def availableImplicitForRegularClass(): Unit = {
    assertEquals(
      classOf[RegularClass],
      implicitly[Stepable[RegularClass]].asJavaType
    )
  }

  @Test
  def availableImplicitForSimpleJavaList(): Unit = {
    val javaType = implicitly[Stepable[JavaList[String]]].asJavaType
    assertTrue(javaType.isInstanceOf[JavaParameterizedType])
    assertEquals(
      classOf[JavaList[_]],
      javaType.asInstanceOf[JavaParameterizedType].getRawType
    )
    assertEquals(
      1L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertEquals(
      classOf[String],
      javaType.asInstanceOf[JavaParameterizedType].getActualTypeArguments()(0)
    )
  }

  @Test
  def availableImplicitForCaseClassJavaList(): Unit = {
    val javaType = implicitly[Stepable[JavaList[SomeCaseClass]]].asJavaType
    assertTrue(javaType.isInstanceOf[JavaParameterizedType])
    assertEquals(
      classOf[JavaList[_]],
      javaType.asInstanceOf[JavaParameterizedType].getRawType
    )
    assertEquals(
      1L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertEquals(
      classOf[SomeCaseClass],
      javaType.asInstanceOf[JavaParameterizedType].getActualTypeArguments()(0)
    )
  }

  @Test
  def availableImplicitForJavaListOfMaps(): Unit = {
    val javaType =
      implicitly[Stepable[JavaList[JavaMap[String, Int]]]].asJavaType
    assertTrue(javaType.isInstanceOf[JavaParameterizedType])
    assertEquals(
      classOf[JavaList[_]],
      javaType.asInstanceOf[JavaParameterizedType].getRawType
    )
    assertEquals(
      1L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertTrue(
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(0)
        .isInstanceOf[JavaParameterizedType]
    )
    assertEquals(
      classOf[JavaMap[_, _]],
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(0)
        .asInstanceOf[JavaParameterizedType]
        .getRawType
    )
    assertEquals(
      2L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(0)
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertEquals(
      classOf[String],
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(0)
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(0)
    )
    assertEquals(
      classOf[Int],
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(0)
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments()(1)
    )
  }

  @Test
  def availableImplicitForRegularClassJavaList(): Unit = {
    val javaType = implicitly[Stepable[JavaList[RegularClass]]].asJavaType
    assertTrue(javaType.isInstanceOf[JavaParameterizedType])
    assertEquals(
      classOf[JavaList[_]],
      javaType.asInstanceOf[JavaParameterizedType].getRawType
    )
    assertEquals(
      1L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertEquals(
      classOf[RegularClass],
      javaType.asInstanceOf[JavaParameterizedType].getActualTypeArguments()(0)
    )
  }

  @Test
  def availableImplicitForSimpleJavaMap(): Unit = {
    val javaType = implicitly[Stepable[JavaMap[String, Int]]].asJavaType
    assertTrue(javaType.isInstanceOf[JavaParameterizedType])
    assertEquals(
      classOf[JavaMap[_, _]],
      javaType.asInstanceOf[JavaParameterizedType].getRawType
    )
    assertEquals(
      2L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertEquals(
      classOf[String],
      javaType.asInstanceOf[JavaParameterizedType].getActualTypeArguments()(0)
    )
    assertEquals(
      classOf[Int],
      javaType.asInstanceOf[JavaParameterizedType].getActualTypeArguments()(1)
    )
  }

  @Test
  def availableImplicitForGenericClass(): Unit = {
    val javaType = implicitly[Stepable[GenericClass[Int]]].asJavaType
    assertTrue(javaType.isInstanceOf[JavaParameterizedType])
    assertEquals(
      classOf[GenericClass[_]],
      javaType.asInstanceOf[JavaParameterizedType].getRawType
    )
    assertEquals(
      1L,
      javaType
        .asInstanceOf[JavaParameterizedType]
        .getActualTypeArguments
        .length
        .toLong
    )
    assertEquals(
      classOf[Int],
      javaType.asInstanceOf[JavaParameterizedType].getActualTypeArguments()(0)
    )
  }

}
