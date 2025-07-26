package io.cucumber.scala

import scala.reflect.ClassTag

private[scala] trait DataTableTypeDsl extends BaseScalaDsl { self =>

  /** Register a data table type.
    */
  def DataTableType: DataTableTypeBody = DataTableType(NO_REPLACEMENT)

  /** Register a data table type with a replacement. <p> A data table can only
    * represent absent and non-empty strings. By replacing a known value (for
    * example [empty]) a data table can also represent empty strings.
    *
    * @param replaceWithEmptyString
    *   a string that will be replaced with an empty string.
    */
  def DataTableType(replaceWithEmptyString: String): DataTableTypeBody =
    DataTableType(Seq(replaceWithEmptyString))

  private def DataTableType(replaceWithEmptyString: Seq[String]) =
    new DataTableTypeBody(replaceWithEmptyString)

  final class DataTableTypeBody(replaceWithEmptyString: Seq[String]) {

    def apply[T](
        body: DataTableEntryDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableEntryTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

    def apply[T](
        body: DataTableOptionalEntryDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableOptionalEntryTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

    def apply[T](
        body: DataTableRowDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableRowTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

    def apply[T](
        body: DataTableOptionalRowDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableOptionalRowTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

    def apply[T](
        body: DataTableCellDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableCellTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

    def apply[T](
        body: DataTableOptionalCellDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableOptionalCellTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

    def apply[T](
        body: DataTableDefinitionBody[T]
    )(implicit ev: ClassTag[T]): Unit = {
      registry.registerDataTableType(
        ScalaDataTableTableTypeDetails[T](
          replaceWithEmptyString,
          body,
          ev,
          Utils.frame(self)
        )
      )
    }

  }

}
