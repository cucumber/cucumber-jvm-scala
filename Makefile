SHELL := /usr/bin/env bash

default:
	sbt clean +publishLocal
.PHONY: default

VERSION = $(shell sbt "print cucumberScala/version" | tail -n 1)

clean:
	sbt clean
.PHONY: clean

update-installdoc:
    # TODO sbt!
	cat docs/install.md | ./scripts/update-install-doc.sh $(VERSION) > docs/install.md.tmp
	mv docs/install.md.tmp docs/install.md
.PHONY: update-installdoc

update-changelog:
	cat CHANGELOG.md | ./scripts/update-changelog.sh $(VERSION) > CHANGELOG.md.tmp
	mv CHANGELOG.md.tmp CHANGELOG.md
.PHONY: update-changelog

prepare-release: update-changelog update-installdoc
	git commit -am "Update CHANGELOG and docs for v$(VERSION)"
	git push
.PHONY: prepare-release
