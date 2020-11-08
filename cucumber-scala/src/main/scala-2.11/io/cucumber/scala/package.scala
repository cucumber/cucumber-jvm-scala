package io.cucumber

import java.lang.reflect.Type
import java.util.function.Supplier
import java.util.{List => JavaList, Map => JavaMap}

import io.cucumber.cucumberexpressions.{CaptureGroupTransformer, ParameterByTypeTransformer}
import io.cucumber.datatable._
import io.cucumber.docstring.DocStringType.Transformer

/**
 * Only for Scala 2.11 which does not support some Single Abstract Method
 */
package object scala {

  import language.implicitConversions

  // Cucumber Scala Types

  implicit def function1AsDataTableEntryDefinitionBody[T](f: (Map[String, String]) => T): DataTableEntryDefinitionBody[T] = new DataTableEntryDefinitionBody[T] {
    override def transform(entry: Map[String, String]): T = f.apply(entry)
  }

  implicit def function1AsDataTableOptionalEntryDefinitionBody[T](f: (Map[String, Option[String]]) => T): DataTableOptionalEntryDefinitionBody[T] = new DataTableOptionalEntryDefinitionBody[T] {
    override def transform(entry: Map[String, Option[String]]): T = f.apply(entry)
  }

  implicit def function1AsDataTableRowDefinitionBody[T](f: (Seq[String]) => T): DataTableRowDefinitionBody[T] = new DataTableRowDefinitionBody[T] {
    override def transform(row: Seq[String]): T = f.apply(row)
  }

  implicit def function1AsDataTableOptionalRowDefinitionBody[T](f: (Seq[Option[String]]) => T): DataTableOptionalRowDefinitionBody[T] = new DataTableOptionalRowDefinitionBody[T] {
    override def transform(row: Seq[Option[String]]): T = f.apply(row)
  }

  implicit def function1AsDataTableCellDefinitionBody[T](f: (String) => T): DataTableCellDefinitionBody[T] = new DataTableCellDefinitionBody[T] {
    override def transform(cell: String): T = f.apply(cell)
  }

  implicit def function1AsDataTableOptionalCellDefinitionBody[T](f: (Option[String]) => T): DataTableOptionalCellDefinitionBody[T] = new DataTableOptionalCellDefinitionBody[T] {
    override def transform(cell: Option[String]): T = f.apply(cell)
  }

  implicit def function1AsDataTableDefinitionBody[T](f: (DataTable) => T): DataTableDefinitionBody[T] = new DataTableDefinitionBody[T] {
    override def transform(dataTable: DataTable): T = f.apply(dataTable)
  }

  // Cucumber Core Types

  implicit def function1AsTableCellTransformer[T](f: String => T): TableCellTransformer[T] = {
    new TableCellTransformer[T] {
      override def transform(cell: String): T = f.apply(cell)
    }
  }

  implicit def function1AsTableTransformer[T](f: DataTable => T): TableTransformer[T] = {
    new TableTransformer[T] {
      override def transform(table: DataTable): T = f.apply(table)
    }
  }

  implicit def function1AsTableEntryTransformer[T](f: JavaMap[String, String] => T): TableEntryTransformer[T] = {
    new TableEntryTransformer[T] {
      override def transform(entry: JavaMap[String, String]): T = f.apply(entry)
    }
  }

  implicit def function1AsTableRowTransformer[T](f: JavaList[String] => T): TableRowTransformer[T] = {
    new TableRowTransformer[T] {
      override def transform(row: JavaList[String]): T = f.apply(row)
    }
  }

  implicit def function1AsTableCellByTypeTransformer(f: (String, Type) => AnyRef): TableCellByTypeTransformer = {
    new TableCellByTypeTransformer {
      override def transform(fromValue: String, toTypeValue: Type): AnyRef = {
        f.apply(fromValue, toTypeValue)
      }
    }
  }

  implicit def function1AsTableEntryByTypeTransformer(f: (JavaMap[String, String], Type, TableCellByTypeTransformer) => AnyRef): TableEntryByTypeTransformer = {
    new TableEntryByTypeTransformer {
      override def transform(fromValue: JavaMap[String, String], toValueType: Type, tableCellByTypeTransformer: TableCellByTypeTransformer): AnyRef = {
        f.apply(fromValue, toValueType, tableCellByTypeTransformer)
      }
    }
  }

  implicit def function1AsParameterByTypeTransformer(f: (String, Type) => AnyRef): ParameterByTypeTransformer = {
    new ParameterByTypeTransformer {
      override def transform(fromValue: String, toValue: Type): AnyRef = {
        f.apply(fromValue, toValue)
      }
    }
  }

  implicit def function1AsTransformer[T](f: (String) => T): Transformer[T] = {
    new Transformer[T] {
      override def transform(s: String): T = {
        f.apply(s)
      }
    }
  }

  implicit def function1AsCaptureGroupTransformer[T](f: (Array[String]) => T): CaptureGroupTransformer[T] = {
    new CaptureGroupTransformer[T] {
      override def transform(parameterContent: Array[String]): T = {
        f.apply(parameterContent)
      }
    }
  }

  // For tests

  implicit def function1AsSupplier[T](f: () => T): Supplier[T] = {
    new Supplier[T] {
      override def get(): T = f.apply()
    }
  }

}
