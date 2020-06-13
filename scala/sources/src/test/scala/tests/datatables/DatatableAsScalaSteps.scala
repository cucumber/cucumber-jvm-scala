package tests.datatables

import io.cucumber.datatable.DataTable
import io.cucumber.scala.Implicits.ScalaDataTable
import io.cucumber.scala.{EN, ScalaDsl}

class DatatableAsScalaSteps extends ScalaDsl with EN {

  Given("the following table as Scala DataTable") { (table: DataTable) =>
    val data: Seq[Map[String, Option[String]]] = table.asScalaDataTable.asScalaMaps
    val expected = Seq(
      Map("key1" -> Some("val11"), "key2" -> Some("val12"), "key3" -> Some("val13")),
      Map("key1" -> Some("val21"), "key2" -> None, "key3" -> Some("val23")),
      Map("key1" -> Some("val31"), "key2" -> Some("val32"), "key3" -> Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala List of Map") { (table: DataTable) =>
    val data: Seq[Map[String, Option[String]]] = table.asScalaMaps
    val expected = Seq(
      Map("key1" -> Some("val11"), "key2" -> Some("val12"), "key3" -> Some("val13")),
      Map("key1" -> Some("val21"), "key2" -> None, "key3" -> Some("val23")),
      Map("key1" -> Some("val31"), "key2" -> Some("val32"), "key3" -> Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala List of List") { (table: DataTable) =>
    val data: Seq[Seq[Option[String]]] = table.asScalaLists
    val expected = Seq(
      Seq(Some("val11"), Some("val12"), Some("val13")),
      Seq(Some("val21"), None, Some("val23")),
      Seq(Some("val31"), Some("val32"), Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala Map of Map") { (table: DataTable) =>
    val data: Map[String, Map[String, Option[String]]] = table.asScalaRowColumnMap
    val expected = Map(
      "row1" -> Map("key1" -> Some("val11"), "key2" -> Some("val12"), "key3" -> Some("val13")),
      "row2" -> Map("key1" -> Some("val21"), "key2" -> None, "key3" -> Some("val23")),
      "row3" -> Map("key1" -> Some("val31"), "key2" -> Some("val32"), "key3" -> Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala Map of List") { (table: DataTable) =>
    val data: Map[String, Seq[Option[String]]] = table.asScalaRowMap
    val expected = Map(
      "row1" -> Seq(Some("val11"), Some("val12"), Some("val13")),
      "row2" -> Seq(Some("val21"), None, Some("val23")),
      "row3" -> Seq(Some("val31"), Some("val32"), Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala Map") { (table: DataTable) =>
    val data: Map[String, Option[String]] = table.asScalaMap[String, String]
    val expected = Map(
      "row1" -> Some("val11"),
      "row2" -> None,
      "row3" -> Some("val31")
    )
    assert(data == expected)
  }

  Given("the following table as Scala List") { (table: DataTable) =>
    val data: Seq[Option[String]] = table.asScalaList
    val expected = Seq(
      Some("val11"),
      None,
      Some("val31")
    )
    assert(data == expected)
  }

  Given("the following table as Scala DataTable of integers") { (table: DataTable) =>
    val data: Seq[Map[Int, Option[Int]]] = table.asScalaDataTable.asScalaMaps[Int, Int]
    val expected = Seq(
      Map(1 -> Some(11), 2 -> Some(12), 3 -> Some(13)),
      Map(1 -> Some(21), 2 -> None, 3 -> Some(23)),
      Map(1 -> Some(31), 2 -> Some(32), 3 -> Some(33))
    )
    assert(data == expected)
  }

  Given("the following table as Scala List of Map of integers") { (table: DataTable) =>
    val data: Seq[Map[Int, Option[Int]]] = table.asScalaMaps[Int, Int]
    val expected = Seq(
      Map(1 -> Some(11), 2 -> Some(12), 3 -> Some(13)),
      Map(1 -> Some(21), 2 -> None, 3 -> Some(23)),
      Map(1 -> Some(31), 2 -> Some(32), 3 -> Some(33))
    )
    assert(data == expected)
  }

  Given("the following table as Scala List of List of integers") { (table: DataTable) =>
    val data: Seq[Seq[Option[Int]]] = table.asScalaLists[Int]
    val expected = Seq(
      Seq(Some(11), Some(12), Some(13)),
      Seq(Some(21), None, Some(23)),
      Seq(Some(31), Some(32), Some(33))
    )
    assert(data == expected)
  }

  Given("the following table as Scala Map of Map of integers") { (table: DataTable) =>
    val data: Map[Int, Map[String, Option[String]]] = table.asScalaRowColumnMap[Int]
    val expected = Map(
      10 -> Map("key1" -> Some("val11"), "key2" -> Some("val12"), "key3" -> Some("val13")),
      20 -> Map("key1" -> Some("val21"), "key2" -> None, "key3" -> Some("val23")),
      30 -> Map("key1" -> Some("val31"), "key2" -> Some("val32"), "key3" -> Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala Map of List of integers") { (table: DataTable) =>
    val data: Map[Int, Seq[Option[String]]] = table.asScalaRowMap[Int]
    val expected = Map(
      10 -> Seq(Some("val11"), Some("val12"), Some("val13")),
      20 -> Seq(Some("val21"), None, Some("val23")),
      30 -> Seq(Some("val31"), Some("val32"), Some("val33"))
    )
    assert(data == expected)
  }

  Given("the following table as Scala Map of integers") { (table: DataTable) =>
    val data: Map[Int, Option[Int]] = table.asScalaMap[Int, Int]
    val expected = Map(
      10 -> Some(11),
      20 -> None,
      30 -> Some(31)
    )
    assert(data == expected)
  }

  Given("the following table as Scala List of integers") { (table: DataTable) =>
    val data: Seq[Option[Int]] = table.asScalaList[Int]
    val expected = Seq(
      Some(11),
      None,
      Some(31)
    )
    assert(data == expected)
  }

  case class CustomType(key1: String, key2: Option[String], key3: String)

  DataTableType { map: Map[String, String] =>
    CustomType(map("key1"), Option(map("key2")), map("key3"))
  }

  Given("the following table as Scala List of custom type") { (table: DataTable) =>
    val data: Seq[CustomType] = table.asScalaRawList[CustomType]
    val expected = Seq(
      CustomType("val11", Some("val12"), "val13"),
      CustomType("val21", None, "val23"),
      CustomType("val31", Some("val32"), "val33")
    )
    assert(data == expected)
  }

  case class RichCell(content: Option[String])

  DataTableType { cell: String =>
    RichCell(Option(cell))
  }

  Given("the following table as Scala List of List of custom type") { (table: DataTable) =>
    val data: Seq[Seq[RichCell]] = table.asScalaRawLists[RichCell]
    val expected = Seq(
      Seq(RichCell(Some("val11")), RichCell(Some("val12")), RichCell(Some("val13"))),
      Seq(RichCell(Some("val21")), RichCell(None), RichCell(Some("val23"))),
      Seq(RichCell(Some("val31")), RichCell(Some("val32")), RichCell(Some("val33")))
    )
    assert(data == expected)
  }

  Given("the following table as Scala List of Map of custom type") { (table: DataTable) =>
    val data: Seq[Map[String, RichCell]] = table.asScalaRawMaps[String, RichCell]
    val expected = Seq(
      Map("key1" -> RichCell(Some("val11")), "key2" -> RichCell(Some("val12")), "key3" -> RichCell(Some("val13"))),
      Map("key1" -> RichCell(Some("val21")), "key2" -> RichCell(None), "key3" -> RichCell(Some("val23"))),
      Map("key1" -> RichCell(Some("val31")), "key2" -> RichCell(Some("val32")), "key3" -> RichCell(Some("val33")))
    )
    assert(data == expected)

  }

}
