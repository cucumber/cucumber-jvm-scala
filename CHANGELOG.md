# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) and this project adheres to [Semantic Versioning](http://semver.org/).

See also the [CHANGELOG](https://github.com/cucumber/cucumber-jvm/blob/master/CHANGELOG.md) from Cucumber Core.

----
## [Unreleased] (In Git)

### Added

- [Scala] Support for Jackson 3.x with `Jackson3DefaultDataTableEntryTransformer` trait
    - Jackson 2.x is still supported with `JacksonDefaultDataTableEntryTransformer` trait

### Changed

### Deprecated

### Removed

### Fixed

## [8.34.0] (2025-10-03)

### Changed

- [Core] Update `cucumber-core` dependency to [7.30.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.33.0] (2025-09-22)

### Added

- [Scala] `ScalaSnippet` now implements `language()` method from `io.cucumber.core.backend.Snippet`

### Changed

- [Core] Update `cucumber-core` dependency to [7.29.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.32.2] (2025-09-09)

### Changed

- [Core] Update `cucumber-core` dependency to [7.28.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.32.1] (2025-09-04)

### Changed

- [Core] Update `cucumber-core` dependency to [7.28.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.32.0] (2025-09-01)

### Changed

- [Core] Update `cucumber-core` dependency to [7.28.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- Upgrade Scala versions to 2.12.20 and 3.3.6

## [8.31.1] (2025-08-19)

### Changed

- [Core] Update `cucumber-core` dependency to [7.27.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.31.0] (2025-07-28)

### Changed

- [Core] Update `cucumber-core` dependency to [7.27.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.30.1] (2025-07-26)

### Changed

- [Internal] Fix `getLocation` wherever it was wrongly defined. ([#404](https://github.com/cucumber/cucumber-jvm-scala/issues/404))

## [8.30.0] (2025-07-23)

### Changed

- [Internal] Implement `HookDefinition.getHookType` and unimplement `Location.getLocation`. No impact expected for users.
([#401](https://github.com/cucumber/cucumber-jvm-scala/issues/401))

## [8.29.0] (2025-07-15)

📢 As of Cucumber JVM 7.24, `cucumber-junit` (JUnit 4) is deprecated in favour of `cucumber-junit-platform-engine`
(JUnit 5). Users are strongly encouraged to use the latter.

📖 Read the "Run" section in [Basic usage](docs/usage.md) to see examples of Cucumber + JUnit 5.

ℹ️ `cucumber-junit-platform-engine` 7.26+ is required when using SBT, lower versions will run Cucumber tests twice and
fail with runtime errors due to [a bug in the SBT/JUnit 5 interface](https://github.com/sbt/sbt-jupiter-interface/issues/142).
Thanks @mpkorstanje for providing a [workaround in Cucumber JVM](https://github.com/cucumber/cucumber-jvm/pull/3023)!

### Added

- [Documentation] Integration with JUnit 5
- Add example project with JUnit 5

### Changed

- [Core] Update `cucumber-core` dependency to [7.26.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Internal] Update integration tests to use JUnit 5
- Drop support of Scala 2.12 in example test projects

## [8.28.0] (2025-05-30)

### Changed

- [Core] Update `cucumber-core` dependency to [7.23.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Update SBT to 1.11.0

## [8.27.3] (2025-05-16)

## [8.27.2] (2025-05-13)

### Changed

- [Core] Update `cucumber-core` dependency to [7.22.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.27.1] (2025-05-09)

### Changed

- [Core] Update `cucumber-core` dependency to [7.22.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Dependencies] chore(deps): update dependency com.fasterxml.jackson.module:jackson-module-scala to v2.19.0
- [Build] Publish to Sonatype Central Host

## [8.27.0] (2025-04-13)

### Changed

- [Core] Update `cucumber-core` dependency to [7.22.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Update SBT to 1.10.11

## [8.26.2] (2025-03-16)

### Changed

- [Build] update `action-publish-sbt`
- [Build] update `sbt-release`

## [8.26.1] (2025-02-10)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.21.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.26.0] (2025-02-03)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.21.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Dependencies] Update Scala version to 2.13.16
- [Dependencies] chore(deps): update dependency org.scala-lang.modules:scala-collection-compat to v2.13.0
- [Build] chore(deps): update dependency com.fasterxml.jackson.module:jackson-module-scala to v2.18.2
- [Build] chore(deps): update dependency sbt/sbt to v1.10.7
- [Build] chore(deps): update dependency org.xerial.sbt:sbt-sonatype to v3.12.2
- [Build] chore(deps): update dependency com.eed3si9n:sbt-projectmatrix to v0.10.1
- [Build] chore(deps): update dependency com.github.sbt:sbt-pgp to v2.3.1
- [Build] chore(deps): update dependency scalafmt to v3.8.6
- [Build] chore(deps): update dependency org.scalameta:sbt-scalafmt to v2.5.4

## [8.25.1] (2024-10-10)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.20.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] chore(deps): update dependency com.github.sbt:sbt-pgp to v2.3.0
- [Build] chore(deps): update dependency org.xerial.sbt:sbt-sonatype to v3.12.0

## [8.25.0] (2024-10-09)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.20.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] chore(deps): update dependency com.fasterxml.jackson.module:jackson-module-scala to v2.18.0

## [8.24.0] (2024-09-24)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.19.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] update dependency scala to v2.13.15
- [Build] update dependency sbt/sbt to v1.10.2
- [Build] update dependency org.xerial.sbt:sbt-sonatype to v3.11.3
- [Build] update dependency scalafmt to v3.8.3

## [8.23.1] (2024-07-19)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.18.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] update dependency sbt/sbt to v1.10.1
- [Build] update dependency com.fasterxml.jackson.module:jackson-module-scala to v2.17.2
- [Build] update dependency com.eed3si9n:sbt-projectmatrix to v0.10.0
- [Build] update dependency org.xerial.sbt:sbt-sonatype to v3.11.0
- [Build] update dependency org.mockito:mockito-scala to v1.17.37
- [Build] update dependency scalafmt to v3.8.2

## [8.23.0] (2024-05-17)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.18.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] update dependency sbt/sbt to v1.10.0
- [Build] update dependency com.fasterxml.jackson.module:jackson-module-scala to v2.17.1
- [Build] update dependency scala to v2.13.14

## [8.22.0] (2024-04-19)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.17.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Upgraded to scalafmt v3
- [Build] Upgraded scala-collection-compat to v2.12.0


## [8.21.1] (2024-03-24)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.16.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.21.0] (2024-03-22)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.16.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Updated `jackson` dependency to 2.17.0
- [Build] Upgraded sbt to 1.9.9
- [Build] Upgraded Scala 2.13.13

## [8.20.0] (2023-12-13)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.15.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.19.0] (2023-11-28)

### Changed

- [Build] Upgraded Scala 2.12.16 to 2.12.18 for JDK 21 purposes. Thanks @dfurmans!

## [8.18.1] (2023-11-26)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.14.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Updated `jackson` dependency to 2.16.0
- [Internal] Updated `sbt-sonatype` to 3.10.0

## [8.18.0] (2023-11-05)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.14.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Upgraded Scala 3 to 3.3.1 (LTS)
- [Build] Updated `jackson` dependency to 2.15.3

## [8.17.0] (2023-08-25)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.13.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.16.0] (2023-06-19)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.12.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Updated `jackson` dependency to 2.15.2
- [Build] Upgraded Scala, `scala-collection-compat`, `sbt`

## [8.15.0] (2023-04-26)

### Changed

- [Build] Updated `jackson` dependency to 2.15.0

## [8.14.2] (2023-03-26)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.11.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.14.1] (2023-01-31)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.11.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.14.0] (2023-01-13)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.11.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.13.1] (2022-12-19)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.10.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.13.0] (2022-12-12)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.10.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Scala] Upgrade Scala 3 version to 3.2.1

- [Scala] Updated `scala-collection-compat` dependency to 2.9.0
- [Build] Updated `jackson` dependency to 2.14.1 
- [Build] Updated `scalafmt` dependency to 2.5.0
- [Build] Updated `sbt-sonatype` dependency to 3.9.15
- [Build] Updated `sbt-version-policy` dependency to 2.1.0
- [Build] Updated `sbt-pgp` dependency to 2.2.1

## [8.12.0] (2022-11-03)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.9.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.11.0] (2022-10-23)

### Changed

- [Scala] Upgrade Scala version to 2.13.10

## [8.10.1] (2022-10-05)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.8.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.10.0] (2022-09-15)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.8.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.9.0] (2022-09-08)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.7.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Upgrade `jackson-module-scala` to 2.13.4

## [8.8.0] (2022-08-21)

### Changed

- [Scala] Upgrade Scala version to 2.12.16, 2.13.8 and 3.1.3
- [sbt] Upgrade sbt to 1.7.1

## [8.7.0] (2022-08-08)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.6.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Upgrade `mockito-scala` to 1.17.12
- [Scala] Upgrade `scala-collection-compat` to 2.8.1

## [8.6.0] (2022-07-28)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.5.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Scala] Upgrade `scala-collection-compat` to 2.8.0

### Fixed

- [Scala] Process glue classes distinctly ([#2582](https://github.com/cucumber/cucumber-jvm/pull/2582))

## [8.5.1] (2022-06-24)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.4.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

### Fixed

- [Build] Fix cross publication to Scala 2.12 and Scala 3 broken since version 8.2.7

## [8.5.0] (2022-06-23)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.4.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.4.0] (2022-05-28)

### Changed

- [Build] Upgrade `sbt` to 1.6.2
- [Build] Upgrade `mockito-scala` to 1.17.7
- [Build] Upgrade `sbt-sonatype` to 3.9.13
- [Build] Upgrade `jackson-module-scala` to 2.13.3

### Fixed

- [Scala] Integration with DI modules like `cucumber-picocontainer` is now working

## [8.3.3] (2022-05-04)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.3.4](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.3.2] (2022-05-01)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.3.3](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.3.1] (2022-04-22)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.3.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.3.0] (2022-04-21)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.3.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.2.8] (2022-04-11)

### Changed

- [Scala] Upgrade `scala-collection-compat` to 2.7.0
- [Build] Upgrade `mockito-scala` to 1.17.5
- [Build] Upgrade workflows `setup-java` to v3
- [Build] Upgrade `sbt-sonatype` to 3.9.11
- [Build] Remove custom gpg command used in releases

## [8.2.7] (2022-03-12)

_This release contains no change, it was built to test a new release workflow._

## [8.2.2] (2022-01-14)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.2.3](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.2.1] (2022-01-10)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.2.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [8.2.0] (2022-01-04)

### Added

- [Scala] Support generic types in `DocStringType`

### Changed

- [Core] Updated `cucumber-core` dependency to [7.2.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Scala] Upgrade `jackson-module-scala` to 2.13.1

## [8.1.0] (2021-11-29)

### Changed

- [Core] Updated `cucumber-core` dependency to [7.1.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Upgrade Scala to 2.12.14, 2.13.6 and 3.0.2
- [Scala] Upgrade `jackson-module-scala` to 2.13.0

## [8.0.0] (2021-10-07)

Check out the [Upgrade Guide](docs/upgrade_v8.md).

### Added

-  [Scala] Added `BeforeAll` and `AfterAll` hooks. See [Hooks](docs/hooks.md).

### Changed

- [Core] Updated `cucumber-core` dependency to [7.0.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

### Removed

- [Scala] Remove support for Scala 2.11
- [Core] Remove deprecated `io.cucumber.scala.TL`

## [7.1.0] (2021-08-06)

### Changed

- [Core] Updated `cucumber-core` dependency to [6.11.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Scala] Upgrade `scala-collection-compat` to 2.5.0
- [Scala] Upgrade `jackson-module-scala` to 2.12.4
- [Build] Upgrade `scalafmt` to 2.4.3

## [7.0.0] (2021-05-15)

📢 This release brings support for Scala 3 but no change in Cucumber core features.

_Although there is no visible change from a user perspective, there are significant changes under the hood (for all Scala versions) that are not binary compatible with Cucumber Scala v6.x (hence the major version change)._

👋 **You are encouraged to use this release even if not targeting Scala 3 yet and report any issue**.

ℹ️ Starting from v7.x, the version of Cucumber Scala will not match anymore the version of core Cucumber projects (like _cucumber-junit_). You should upgrade them independently.

### Added

- [Scala] Support Scala 3
  - Check out the [v7.x upgrade guide](./docs/upgrade_v7.md)

### Changed

- [Internal] Rewrite the way types of step arguments are inferred 

## [6.10.4] (2021-05-14)

### Changed

- [Core] Updated `cucumber-core` dependency to [6.10.4](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Scala] Upgrade `scala-collection-compat` to 2.4.4
- [Build] Upgrade `sbt-projectmatrix` to 0.8.0
- [Build] Upgrade `sbt-version-policy` to 1.0.1

## [6.10.3] (2021-04-15)

### Changed

- [Core] Updated `cucumber-core` dependency to [6.10.3](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.10.2] (2021-03-16)

### Changed

- [Core] Updated `cucumber-core` dependency to [6.10.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.10.1] (2021-03-09)

### Changed

- [Core] Updated `cucumber-core` dependency to [6.10.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Use Github Actions instead of Travis CI
- [Build] Upgrade Scala to 2.12.13 and 2.13.5

## [6.10.0] (2021-02-15)

### Changed

- [Core] Updated `cucumber-core` dependency to [6.10.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Upgrade to sbt 1.4.5

### Deprecated

- [Core] Deprecated `io.cucumber.scala.TL` in favour of `io.cucumber.scala.TE` 

## [6.9.1] (2020-12-15)

### Added

- [Build] Setup formatting using `scalafmt`
- [CI] Build & test on both JDK 8 and 11

### Changed

- [Build] Tested Jackson version is now 2.12.0 
- [Core] Updated `cucumber-core` dependency to [6.9.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.9.0] (2020-11-15)

### Changed

- [Core] Update `cucumber-core` dependency to [6.9.0](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)
- [Build] Use sbt for building the project

## [6.8.2] (2020-10-30)

### Changed

- [Core] Update `cucumber-core` dependency to [6.8.2](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

## [6.8.1] (2020-10-08)

### Changed

- [Core] Update `cucumber-core` dependency to [6.8.1](https://github.com/cucumber/cucumber-jvm/blob/main/CHANGELOG.md)

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
[Unreleased]: https://github.com/cucumber/cucumber-jvm-scala/compare/v8.30.2-SNAPSHOT...HEAD
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.30.2...v8.31.0-SNAPSHOT
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.34.0...v8.31.0-SNAPSHOT
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.33.0...v8.31.0-SNAPSHOT
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.32.2...v8.31.0-SNAPSHOT
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.32.1...v8.31.0-SNAPSHOT
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.32.0...v8.31.0-SNAPSHOT
[8.31.0-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.31.1...v8.31.0-SNAPSHOT
[8.30.2-SNAPSHOT]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.30.1...v8.30.2-SNAPSHOT
[8.30.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.30.0...v8.30.1
[8.30.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.29.0...v8.30.0
[8.29.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.28.0...v8.29.0
[8.28.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.27.3...v8.28.0
[8.27.3]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.27.2...v8.27.3
[8.27.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.27.1...v8.27.2
[8.27.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.27.0...v8.27.1
[8.27.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.26.2...v8.27.0
[8.26.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.26.1...v8.26.2
[8.26.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.26.0...v8.26.1
[8.26.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.25.1...v8.26.0
[8.25.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.25.0...v8.25.1
[8.25.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.24.0...v8.25.0
[8.24.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.23.1...v8.24.0
[8.23.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.23.0...v8.23.1
[8.23.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.22.0...v8.23.0
[8.22.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.21.1...v8.22.0
[8.21.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.21.0...v8.21.1
[8.21.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.20.0...v8.21.0
[8.20.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.19.0...v8.20.0
[8.19.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.18.1...v8.19.0
[8.18.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.18.0...v8.18.1
[8.18.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.17.0...v8.18.0
[8.17.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.16.0...v8.17.0
[8.16.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.15.0...v8.16.0
[8.15.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.14.2...v8.15.0
[8.14.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.14.1...v8.14.2
[8.14.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.14.0...v8.14.1
[8.14.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.13.1...v8.14.0
[8.13.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.13.0...v8.13.1
[8.13.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.12.0...v8.13.0
[8.12.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.11.0...v8.12.0
[8.11.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.10.1...v8.11.0
[8.10.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.10.0...v8.10.1
[8.10.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.9.0...v8.10.0
[8.9.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.8.0...v8.9.0
[8.8.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.7.0...v8.8.0
[8.7.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.6.0...v8.7.0
[8.6.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.5.1...v8.6.0
[8.5.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.5.0...v8.5.1
[8.5.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.4.0...v8.5.0
[8.4.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.3.3...v8.4.0
[8.3.3]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.3.2...v8.3.3
[8.3.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.3.1...v8.3.2
[8.3.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.3.0...v8.3.1
[8.3.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.2.8...v8.3.0
[8.2.8]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.2.7...v8.2.8
[8.2.7]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.2.2...v8.2.7
[8.2.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.2.1...v8.2.2
[8.2.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.2.0...v8.2.1
[8.2.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.1.0...v8.2.0
[8.1.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v8.0.0...v8.1.0
[8.0.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v7.1.0...v8.0.0
[7.1.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v7.0.0...v7.1.0
[7.0.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.10.4...v7.0.0
[6.10.4]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.10.3...v6.10.4
[6.10.3]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.10.2...v6.10.3
[6.10.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.10.1...v6.10.2
[6.10.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.10.0...v6.10.1
[6.10.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.9.1...v6.10.0
[6.9.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.9.0...v6.9.1
[6.9.0]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.8.2...v6.9.0
[6.8.2]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.8.1...v6.8.2
[6.8.1]:  https://github.com/cucumber/cucumber-jvm-scala/compare/v6.8.0...v6.8.1
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
