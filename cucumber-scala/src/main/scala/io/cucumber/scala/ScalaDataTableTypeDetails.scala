package io.cucumber.scala

import scala.reflect.ClassTag

sealed trait ScalaDataTableTypeDetails[T] {
  def emptyPatterns: Seq[String]
  def tag: ClassTag[T]
  def stackTraceElement: StackTraceElement
}

case class ScalaDataTableEntryTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableEntryDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]

case class ScalaDataTableOptionalEntryTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableOptionalEntryDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]

case class ScalaDataTableRowTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableRowDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]

case class ScalaDataTableOptionalRowTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableOptionalRowDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]

case class ScalaDataTableCellTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableCellDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]

case class ScalaDataTableOptionalCellTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableOptionalCellDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]

case class ScalaDataTableTableTypeDetails[T](
    emptyPatterns: Seq[String],
    body: DataTableDefinitionBody[T],
    tag: ClassTag[T],
    stackTraceElement: StackTraceElement
) extends ScalaDataTableTypeDetails[T]
