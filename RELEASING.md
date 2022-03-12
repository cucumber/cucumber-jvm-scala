Releasing
=========

Releases are automated via a [GitHub Actions workflow](./.github/workflows/release-sbt.yml). Only people with permission to push to `release/*` branches can make releases.

See [Cucumber release process](https://github.com/cucumber/.github/blob/main/RELEASING.md) for the whole process.

## Preparation

1. Decide what the next version should be according to semver
   ```bash
   export next_release=<version> # <- insert version number here
   ```
1. Update the `version.sbt` file with version to release:
    ```bash
    echo "ThisBuild / version := \"$next_release\"" > version.sbt
    ```
1. Update the CHANGELOG and documentation, commit and push:
    ```bash
    make prepare-release
    ```

## Release

1. Push to a new `release/*` branch to trigger the `release-*` workflows
   ```bash
   git push origin main:release/v$next_release
   ```
1. Wait until the `release-*` workflows in GitHub Actions have passed
1. In `version.sbt`, bump the **patch** version and append `-SNAPSHOT` (e.g. `1.2.4-SNAPSHOT`) and commit/push
1. Announce the release
   * in the `#newsletter` Slack channel
   * on the `@cucumberbdd` Twitter account
   * write a blog post
