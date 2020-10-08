# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) and this project adheres to [Semantic Versioning](http://semver.org/).

See also the [CHANGELOG](https://github.com/cucumber/cucumber-jvm/blob/master/CHANGELOG.md) from Cucumber Core.

----
## [Unreleased] (In Git)

### Added

### Changed

- [Core] Update `cucumber-core` dependency to [6.8.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

### Deprecated

### Removed

### Fixed

## [6.8.0] (2020-09-27)

### Changed

- [Core] Update `cucumber-core` dependency to [6.8.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.7.0] (2020-09-16)

### Changed

- [Core] Update `cucumber-core` dependency to [6.7.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.6.0] (2020-09-04)

### Changed

- [Core] Update `cucumber-core` dependency to [6.6.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.4.0] (2020-08-01)

### Changed

- [Core] Update `cucumber-core` dependency to [6.4.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Update Scala versions to 2.12.12

## [6.3.0] (2020-07-26)

### Changed

- [Core] Update `cucumber-core` dependency to [6.3.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.2.2] (2020-07-09)

### Changed

- [Core] Update `cucumber-core` dependency to [6.2.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

### Fixed

- [JUnit] [sbt] Fix failed scenarios not failing the test suite if using duplicated names or `Scenario Outline` ([#22](https://github.com/cucumber/cucumber-jvm-scala/issues/102) [#102](https://github.com/cucumber/cucumber-jvm-scala/issues/102) M.P. Korstanje)
  - The fix is actually part of `cucumber-junit` 6.2.2 (https://github.com/cucumber/cucumber-jvm/pull/2045) but is mainly done for Cucumber Scala and Sbt usage

## [6.2.1] (2020-07-07)

### Changed

- [Core] Update `cucumber-core` dependency to [6.2.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.2.0] (2020-07-04)

### Added

- Add `asScalaRawList[T]`, `asScalaRawMaps[T]` and `asScalaRawLists[T]` on `DataTable` (through `io.cucumber.scala.Implicits`) ([#83](https://github.com/cucumber/cucumber-jvm-scala/issues/83) Gaël Jourdan-Weil)
- Add new `DataTableType` definitions with optional input values ([#84](https://github.com/cucumber/cucumber-jvm-scala/issues/84) Gaël Jourdan-Weil)
  - `DataTableType { (entry: Map[String, Option[String]]) => ... }`
  - `DataTableType { (row: Seq[Option[String]]) => ... }`
  - `DataTableType { (cell: Option[String]) => ... }`

### Changed

- [Core] Update `cucumber-core` dependency to [6.2.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.1.2] (2020-06-26)

### Changed

- [Core] Update `cucumber-core` dependency to 6.1.2
- [Build] Update Scala versions to 2.13.3

## [6.1.1] (2020-06-12)

### Changed
- [Core] Update `cucumber-core` dependency to 6.1.1

## [6.0.0] (2020-06-07)

### Added

- [Scala] Conversion methods from `DataTable` to scala types ([#56](https://github.com/cucumber/cucumber-jvm-scala/issues/56) Gaël Jourdan-Weil)
- [Scala] Add `attach(String, String, String)` in `Scenario` (Gaël Jourdan-Weil)

### Changed

- [Core] Update `cucumber-core` dependency to 6.0.0 (Gaël Jourdan-Weil)

### Removed

- [Scala] Remove deprecated methods in `Scenario` (Gaël Jourdan-Weil)

### Fixed

- [Scala DSL] Raise an exception at runtime if hooks are not correctly defined ([#60](https://github.com/cucumber/cucumber-jvm-scala/issues/60) Gaël Jourdan-Weil)

## [5.7.0] (2020-05-10)

### Added

- [Scala] `Scenario.log(String)` & `Scenario.attach(byte[], String, String)` ([#42](https://github.com/cucumber/cucumber-jvm-scala/pull/42) Gaël Jourdan-Weil)
- [Doc] Added Javadoc on `ScalaDsl` methods ([#53](https://github.com/cucumber/cucumber-jvm-scala/issues/53) Gaël Jourdan-Weil)

### Changed

- [Core] Update `cucumber-core` dependency to 5.7.0 ([#42](https://github.com/cucumber/cucumber-jvm-scala/pull/42) Gaël Jourdan-Weil)
- [Build] Update Scala versions to 2.13.2 ([#23](https://github.com/cucumber/cucumber-jvm-scala/issues/43) Gaël Jourdan-Weil)

### Deprecated

- [Scala] `Scenario.write(String)` & `Scenario.embed(byte[], String, String)` ([#42](https://github.com/cucumber/cucumber-jvm-scala/pull/42) Gaël Jourdan-Weil)

### Fixed

- [Build] Remove build warnings ([#45](https://github.com/cucumber/cucumber-jvm-scala/issues/45) Gaël Jourdan-Weil)

## [5.6.0] (2020-05-03)

### Added

- [Doc] Users documentation on Scala DSL
- [Scala DSL] Support for transformers ([#32](https://github.com/cucumber/cucumber-jvm-scala/issues/32) Gaël Jourdan-Weil) 
  - See [Transformers](docs/transformers.md)
- [Transformers] Add optional `JacksonDefaultDataTableEntryTransformer` ([#31](https://github.com/cucumber/cucumber-jvm-scala/issues/31) Gaël Jourdan-Weil)
  - See [here](docs/default_jackson_datatable_transformer.md)
- [Scala DSL] Support hooks with by name body ([#26](https://github.com/cucumber/cucumber-jvm-scala/issues/26) Gaël Jourdan-Weil)

### Changed

- [Core] Update `cucumber-core` dependency to 5.6.0 ([#23](https://github.com/cucumber/cucumber-jvm-scala/issues/23) Gaël Jourdan-Weil)
  - Package move from `cucumber.api.scala` to `io.cucumber.scala`
  - Hooks definition changed
  - See [Upgrade Guide](docs/upgrade_v5.md)
- [Build] Update Scala versions to 2.12.11 and 2.13.1 ([#23](https://github.com/cucumber/cucumber-jvm-scala/issues/23) Gaël Jourdan-Weil)
- [Gherkin] Update Gherkin version to 9.2.0 
  - New `MR` and `ME` traits available

### Fixed

- [Core] Instantiate glue classes per scenario ([#1](https://github.com/cucumber/cucumber-jvm-scala/issues/1) Gaël Jourdan-Weil)

## [4.7.1] (2019-08-01)

### Changed

- [Core] Update `cucumber-core` dependency to 4.7.1 (Glib Briia)

## [4.7.0] (2019-08-01)

### Changed

- [Core] Update `cucumber-core` dependency to 4.7.0 (Glib Briia)

## [4.6.0] (2019-08-01)

### Changed

- [Core] Update `cucumber-core` dependency to 4.6.0 (Glib Briia)

## [4.5.4] (2019-08-01)

### Changed

- [Core] Update `cucumber-core` dependency to 4.5.4 (Glib Briia)

## [4.5.3] (2019-07-10)

### Changed

- [Core] Update `cucumber-core` dependency to 4.5.3 (Glib Briia)

## [4.4.0] (2019-06-18)

### Changed

- [Core] Update `cucumber-core` dependency to 4.4.0 (Glib Briia)

## [4.3.1] (2019-05-15)

### Changed

- [Core] Update `cucumber-core` dependency to 4.3.1 (Glib Briia)

## [4.3.0] (2019-04-26)

### Changed

- [Core] Update `cucumber-core` dependency to 4.3.0 (Glib Briia)

## [4.2.6] (2019-03-12)

### Changed

- [Core] Update `cucumber-core` dependency to 4.2.6 (Glib Briia)

## [4.2.0] (2019-03-12)

### Changed

- [Core] Update `cucumber-core` dependency to 4.2.0 (Glib Briia)

## [4.1.0] (2018-11-04)

### Changed

- [Core] Update `cucumber-core` dependency to 4.1.0 (Glib Briia)
- [Build] Update Scala versions to 2.11.12 and 2.12.7 ([#11](https://github.com/cucumber/cucumber-jvm-scala/issues/11) Arturas Smorgun)

<!-- Releases -->
[Unreleased]: https://github.com/cucumber/cucumber-jvm-scala/compare/v6.8.0...main
[6.8.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.7.0...v6.8.0
[6.7.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.6.0...v6.7.0
[6.6.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.4.0...v6.6.0
[6.4.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.3.0...v6.4.0
[6.3.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.2.2...v6.3.0
[6.2.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.2.1...v6.2.2
[6.2.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.2.0...v6.2.1
[6.2.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.1.2...v6.2.0
[6.1.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.1.1...v6.1.2
[6.1.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.0.0...v6.1.1
[6.0.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v5.7.0...v6.0.0
[5.7.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v5.6.0...v5.7.0
[5.6.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.7.1...v5.6.0
[4.7.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.7.0...v4.7.1
[4.7.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.6.0...v4.7.0
[4.6.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.5.4...v4.6.0
[4.5.4]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.5.3...v4.5.4
[4.5.3]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.4.0...v4.5.3
[4.4.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.3.1...v4.4.0
[4.3.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.3.0...v4.3.1
[4.3.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.2.6...v4.3.0
[4.2.6]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.2.0...v4.2.6
[4.2.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v4.1.0...v4.2.0
