package io.cucumber.scala

import io.cucumber.datatable.DataTable

import scala.jdk.CollectionConverters._
import scala.reflect.ClassTag

/** Contains implicit helpers for Cucumber Scala users.
  */
object Implicits {

  /** DataTable extension class providing methods to read a DataTable as Scala
    * types. <p> <em>Note: we do not filter out null values because users might
    * rely on the keyset in their implementation.</em>
    */
  implicit class ScalaDataTable(table: DataTable) {

    def asScalaDataTable: ScalaDataTable = this

    /** Provides a view of the DataTable as a sequence of rows, each row being a
      * key-value map where key is the column name. Equivalent of
      * `.asMaps[K,V](classOf[K], classOf[V])` but returned as Scala collection
      * types without `null` values.
      *
      * See also `asScalaRawMaps[T]` if you don't need `Option` s (for instance
      * if you are using a DataTableType).
      *
      * @tparam K
      *   key type
      * @tparam V
      *   value type
      * @return
      *   sequence of rows
      */
    def asScalaMaps[K, V](implicit
        evK: ClassTag[K],
        evV: ClassTag[V]
    ): Seq[Map[K, Option[V]]] = {
      table
        .asMaps[K, V](evK.runtimeClass, evV.runtimeClass)
        .asScala
        .map(_.asScala.map(nullToNone).toMap)
        .toSeq
    }

    /** Provides a view of the DataTable as a sequence of rows, each row being a
      * key-value map where key is the column name. Equivalent of `.asMaps()`
      * but returned as Scala collection types without `null` values.
      *
      * @return
      *   sequence of rows
      */
    def asScalaMaps: Seq[Map[String, Option[String]]] =
      asScalaMaps[String, String]

    /** Provides a view of the DataTable as a sequence of rows, each row being a
      * key-value map where key is the column name. Equivalent of
      * `.asMaps[K,V](classOf[K], classOf[V])` but returned as Scala collection
      * types.
      *
      * See also `asScalaMaps[T]`.
      *
      * @tparam K
      *   key type
      * @tparam V
      *   value type
      * @return
      *   sequence of rows
      */
    def asScalaRawMaps[K, V](implicit
        evK: ClassTag[K],
        evV: ClassTag[V]
    ): Seq[Map[K, V]] = {
      table
        .asMaps[K, V](evK.runtimeClass, evV.runtimeClass)
        .asScala
        .map(_.asScala.toMap)
        .toSeq
    }

    /** Provides a view of the DataTable as a key-value map where key are the
      * first column values. Equivalent of `.asMap[K,V](classOf[K],classOf[V])`
      * but returned as Scala collection types without `null` values.
      *
      * @tparam K
      *   key type
      * @tparam V
      *   value type
      * @return
      *   key-value map
      */
    def asScalaMap[K, V](implicit
        evK: ClassTag[K],
        evV: ClassTag[V]
    ): Map[K, Option[V]] = {
      table
        .asMap[K, V](evK.runtimeClass, evV.runtimeClass)
        .asScala
        .map(nullToNone)
        .toMap
    }

    /** Provides a view of the DataTable as a matrix. Equivalent of
      * `.asLists[T](classOf[T])` but returned as Scala collection types without
      * `null` values.
      *
      * See also `asScalaRawLists[T]` if you don't need `Option` s (for instance
      * if you are using a DataTableType).
      *
      * @tparam T
      *   cell type
      * @return
      *   matrix
      */
    def asScalaLists[T](implicit ev: ClassTag[T]): Seq[Seq[Option[T]]] = {
      table
        .asLists[T](ev.runtimeClass)
        .asScala
        .map(_.asScala.map(Option.apply).toSeq)
        .toSeq
    }

    /** Provides a view of the DataTable as a matrix. Equivalent of `.asLists()`
      * but returned as Scala collection types without `null` values.
      *
      * @return
      *   matrix
      */
    def asScalaLists: Seq[Seq[Option[String]]] = asScalaLists[String]

    /** Provides a view of the DataTable as a matrix. Equivalent of
      * `.asLists[T](classOf[T])` but returned as Scala collection types.
      *
      * See also `asScalaLists[T]`
      *
      * @tparam T
      *   cell type
      * @return
      *   matrix
      */
    def asScalaRawLists[T](implicit ev: ClassTag[T]): Seq[Seq[T]] = {
      table
        .asLists[T](ev.runtimeClass)
        .asScala
        .map(_.asScala.toSeq)
        .toSeq
    }

    /** Provides a view of the DataTable as a simple list of values. Equivalent
      * of `.asList[T](classOf[T])` but returned as Scala collection types
      * without `null` values.
      *
      * See also `asScalaRawList[T]` if you don't need `Option` s (for instance
      * if you are using a DataTableType).
      *
      * @tparam T
      *   cell type
      * @return
      *   list of values
      */
    def asScalaList[T](implicit ev: ClassTag[T]): Seq[Option[T]] = {
      table
        .asList[T](ev.runtimeClass)
        .asScala
        .map(Option.apply)
        .toSeq
    }

    /** Provides a view of the DataTable as a simple list of values. Equivalent
      * of `.asList()` but returned as Scala collection types without `null`
      * values.
      *
      * @return
      *   list of values
      */
    def asScalaList: Seq[Option[String]] = asScalaList[String]

    /** Provides a view of the DataTable as a simple list of values. Equivalent
      * of `.asList[T](classOf[T])` but returned as Scala collection types.
      *
      * See also `asScalaList[T]`.
      *
      * @tparam T
      *   cell/row type
      * @return
      *   list of values
      */
    def asScalaRawList[T](implicit ev: ClassTag[T]): Seq[T] = {
      table.asList[T](ev.runtimeClass).asScala.toSeq
    }

    /** Provides a view of the DataTable as a full table: a key-value map of row
      * where keys are the first column values and each row being itself a
      * key-value map where key is the column name.
      *
      * @tparam K
      *   key type
      * @return
      *   map of rows
      */
    def asScalaRowColumnMap[K](implicit
        evK: ClassTag[K]
    ): Map[K, Map[String, Option[String]]] = {
      table
        .asMap[K, java.util.Map[String, String]](
          evK.runtimeClass,
          classOf[java.util.Map[String, String]]
        )
        .asScala
        .map { case (k, v) => (k, v.asScala.map(nullToNone).toMap) }
        .toMap
    }

    /** Provides a view of the DataTable as a full table: a key-value map of row
      * where keys are the first column values and each row being itself a
      * key-value map where key is the column name.
      *
      * @return
      *   map of rows
      */
    def asScalaRowColumnMap: Map[String, Map[String, Option[String]]] =
      asScalaRowColumnMap[String]

    /** Provides a view of the DataTable as a key-value map of row where keys
      * are the first column values and each row being a list of values.
      *
      * @tparam K
      *   key type
      * @return
      *   map of rows
      */
    def asScalaRowMap[K](implicit
        evK: ClassTag[K]
    ): Map[K, Seq[Option[String]]] = {
      table
        .asMap[K, java.util.List[String]](
          evK.runtimeClass,
          classOf[java.util.List[String]]
        )
        .asScala
        .map { case (k, v) => (k, v.asScala.map(Option.apply).toSeq) }
        .toMap
    }

    /** Provides a view of the DataTable as a key-value map of row where keys
      * are the first column values and each row being a list of values.
      *
      * @return
      *   map of rows
      */
    def asScalaRowMap: Map[String, Seq[Option[String]]] = asScalaRowMap[String]

    private def nullToNone[K, V](tuple: (K, V)): (K, Option[V]) = {
      val (k, v) = tuple
      (k, Option(v))
    }

  }

}
