# Upgrading from 7.x to 8.x

Prior to upgrading to v8.0.0 upgrade to latest v7.x and stop using all deprecated features.
Some features will log a deprecation warning.

See also:
- [Cucumber Scala CHANGELOG](../CHANGELOG.md)
- [Cucumber JVM CHANGELOG](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## Cannot use `DataTable#asX` inside a `DataTableType`

You should not use the methods `DataTable#asX()` in `DataTableType`s.
It was working in previous versions, but it will now raise an exception when running your tests.

Instead you should use:
- Replace `DataTable#asList()` with `DataTable#values()`
- Replace `DataTable#asLists()` with `DataTable#cells()`
- Replace `DataTable#asMaps()` with `DataTable#entries()`


More context for this change at https://github.com/cucumber/common/pull/1419
