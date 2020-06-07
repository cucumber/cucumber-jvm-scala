# Upgrading from 5.x to 6.x

Upgrading from v5 should be straightforward.
Prior to upgrading to v6.0.0 upgrade to v5.7.0 and stop using all deprecated features.
Some features will log a deprecation warning.

See also:
- [Cucumber Scala CHANGELOG](../CHANGELOG.md)
- [Cucumber JVM CHANGELOG](https://github.com/cucumber/cucumber-jvm/blob/master/CHANGELOG.md)
- [Cucumber JVM v6 Release Notes](https://github.com/cucumber/cucumber-jvm/blob/master/release-notes/v6.0.0.md)

## Map DataTables to Scala types

You can now map `DataTable`s to Scala collection types using additional `asScalaXxx` methods on the `DataTable` class.

**The benefit of using Scala types** if that you will be handling `Option`s instead of potentially `null` values in the Java collections.

For instance with `asScalaMaps`:

```scala
import io.cucumber.scala.{ScalaDsl, EN}
import io.cucumber.scala.Implicits._

class StepDefs extends ScalaDsl with EN {

  Given("the following table as List of Map") { (table: DataTable) =>
    val scalaTable: Seq[Map[String, Option[Int]]] = table.asScalaMaps[String, Int]
    // Do something
  }

}
```

See the [DataTable documentation](./datatables.md) for more details.
