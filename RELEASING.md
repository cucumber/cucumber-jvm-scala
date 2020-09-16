Releasing
=========

The deployment process of `cucumber-jvm-scala` is based on 
[Deploying to OSSRH with Apache Maven](http://central.sonatype.org/pages/apache-maven.html#deploying-to-ossrh-with-apache-maven-introduction).

## Check [![Build Status](https://travis-ci.org/cucumber/cucumber-jvm-scala.svg?branch=main)](https://travis-ci.org/cucumber/cucumber-jvm-scala) ##

Is the build passing?

```
git checkout main
```

Also check if you can upgrade any dependencies:

```
make update-dependency-versions
```

## Decide what the next version should be ##

Versions follow [Semantic Versioning](https://semver.org/spec/v2.0.0.html). To sum it up, it depends on what's changed (see `CHANGELOG.md`). Given a version number MAJOR.MINOR.PATCH:

* Bump `MAJOR` when you make incompatible API changes:
  * There are `Removed` entries, or `Changed` entries breaking compatibility
  * A cucumber library dependency upgrade was major
* Bump `MINOR` when you add functionality in a backwards compatible manner:
  * There are `Added` entries, `Changed` entries preserving compatibility, or
  `Deprecated` entries
* Bump `PATCH` when you make backwards compatible bug fixes:
  * There are `Fixed` entries

Display future version by running:

```
make version
```

Check if branch name and version are as expected. To change version run:

```
mvn versions:set -DnewVersion=X.Y.Z-SNAPSHOT
```

## Secrets ##

Secrets are required to make releases. Members of the core team can install
keybase and join the `cucumberbdd` team to access these secrets.

During the release process, secrets are fetched from keybase and used to sign
and upload the maven artifacts.

## Make the release ##

Check if branch name and version are as expected:

```
make version
```

Do the release:

```
make release
``` 

All done! Hurray!

## Troubleshooting

### Error from Docker

If you encounter the following error:
```
Unable to find image 'cucumber/cucumber-build:latest' locally
docker: Error response from daemon: Get https://registry-1.docker.io/v2/: dial tcp: lookup registry-1.docker.io on [::1]:53: read udp [::1]:38683->[::1]:53: read: connection refused.
See 'docker run --help'.
```

You might want to try to download the Docker image by yourself using:
```sh
docker pull cucumber/cucumber-build:latest
```
