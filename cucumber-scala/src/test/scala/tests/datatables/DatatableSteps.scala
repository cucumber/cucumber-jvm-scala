package tests.datatables

import java.util.{List => JavaList, Map => JavaMap}

import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, ScalaDsl}

import scala.jdk.CollectionConverters._

class DatatableSteps extends ScalaDsl with EN {

  Given("the following table as DataTable") { (table: DataTable) =>
    val data: Seq[Map[String, String]] =
      table.asMaps().asScala.map(_.asScala.toMap).toSeq
    val expected = Seq(
      Map("key1" -> "val11", "key2" -> "val12", "key3" -> "val13"),
      Map("key1" -> "val21", "key2" -> "val22", "key3" -> "val23"),
      Map("key1" -> "val31", "key2" -> "val32", "key3" -> "val33")
    )
    assert(data == expected)
  }

  Given("the following table as List of Map") {
    (table: JavaList[JavaMap[String, String]]) =>
      val data: Seq[Map[String, String]] =
        table.asScala.map(_.asScala.toMap).toSeq
      val expected = Seq(
        Map("key1" -> "val11", "key2" -> "val12", "key3" -> "val13"),
        Map("key1" -> "val21", "key2" -> "val22", "key3" -> "val23"),
        Map("key1" -> "val31", "key2" -> "val32", "key3" -> "val33")
      )
      assert(data == expected)
  }

  Given("the following table as List of List") {
    (table: JavaList[JavaList[String]]) =>
      val data: Seq[Seq[String]] = table.asScala.map(_.asScala.toSeq).toSeq
      val expected = Seq(
        Seq("val11", "val12", "val13"),
        Seq("val21", "val22", "val23"),
        Seq("val31", "val32", "val33")
      )
      assert(data == expected)
  }

  Given("the following table as Map of Map") {
    (table: JavaMap[String, JavaMap[String, String]]) =>
      val data: Map[String, Map[String, String]] = table.asScala.map {
        case (k, v) => k -> v.asScala.toMap
      }.toMap
      val expected = Map(
        "row1" -> Map("key1" -> "val11", "key2" -> "val12", "key3" -> "val13"),
        "row2" -> Map("key1" -> "val21", "key2" -> "val22", "key3" -> "val23"),
        "row3" -> Map("key1" -> "val31", "key2" -> "val32", "key3" -> "val33")
      )
      assert(data == expected)
  }

  Given("the following table as Map of List") {
    (table: JavaMap[String, JavaList[String]]) =>
      val data: Map[String, Seq[String]] = table.asScala.map { case (k, v) =>
        k -> v.asScala.toSeq
      }.toMap
      val expected = Map(
        "row1" -> Seq("val11", "val12", "val13"),
        "row2" -> Seq("val21", "val22", "val23"),
        "row3" -> Seq("val31", "val32", "val33")
      )
      assert(data == expected)
  }

  Given("the following table as Map") { (table: JavaMap[String, String]) =>
    val data: Map[String, String] = table.asScala.toMap
    val expected = Map(
      "row1" -> "val11",
      "row2" -> "val21",
      "row3" -> "val31"
    )
    assert(data == expected)
  }

  Given("the following table as List") { (table: JavaList[String]) =>
    val data: Seq[String] = table.asScala.toSeq
    val expected = Seq(
      "val11",
      "val21",
      "val31"
    )
    assert(data == expected)
  }

}
