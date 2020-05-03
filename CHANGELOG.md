# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) and this project adheres to [Semantic Versioning](http://semver.org/).

See also the [CHANGELOG](https://github.com/cucumber/cucumber-jvm/blob/master/CHANGELOG.md) from Cucumber Core.

----

## [Unreleased] (In Git)

### Added

- [Doc] Users documentation on Scala DSL
- [Scala DSL] Support for transformers ([#32](https://github.com/cucumber/cucumber-jvm-scala/issues/32) Gaël Jourdan-Weil) 
  - See [Transformers](docs/transformers.md)
- [Transformers] Add optional `JacksonDefaultDataTableEntryTransformer` ([#31](https://github.com/cucumber/cucumber-jvm-scala/issues/31) Gaël Jourdan-Weil)
  - See [here](docs/default_jackson_datatable_transformer.md)

### Changed

- [Core] Update `cucumber-core` dependency to 5.6.0 ([#23](https://github.com/cucumber/cucumber-jvm-scala/issues/23) Gaël Jourdan-Weil)
  - Package move from `cucumber.api.scala` to `io.cucumber.scala`
  - Hooks definition changed
  - See [Upgrade Guide](docs/upgrade_v5.md)
- [Build] Update Scala versions to 2.12.11 and 2.13.1 ([#23](https://github.com/cucumber/cucumber-jvm-scala/issues/23) Gaël Jourdan-Weil)
- [Gherkin] Update Gherkin version to 9.2.0 
  - New `MR` and `ME` traits available

### Deprecated

### Removed

### Fixed

- [Core] Instantiate glue classes per scenario ([#1](https://github.com/cucumber/cucumber-jvm-scala/issues/1) Gaël Jourdan-Weil)

### Security

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

<!-- References -->
[Unreleased]: https://github.com/cucumber/cucumber-jvm-scala/compare/v4.7.1...master
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