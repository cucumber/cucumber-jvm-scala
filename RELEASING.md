Releasing
=========

Releases are automated via a [GitHub Actions workflow](./.github/workflows/release-sbt.yaml). Only people with permission to create tags can make releases.

See [Cucumber release process](https://github.com/cucumber/.github/blob/main/RELEASING.md) for the whole process.

## Preparation

1. Decide what the next version should be according to semver
   ```bash
   export next_release=<version> # <- insert version number here
   ```
2. Update the CHANGELOG and documentation for the release version, commit and push:
    ```bash
    make prepare-release VERSION="$next_release"
    ```

## Release

1. Push to a new tag to trigger the `release-*` workflows
   ```bash
   git tag --message "v$next_release" "v$next_release"
   git push origin main "v$next_release"
   ```
