SHELL := /usr/bin/env bash

default:
	sbt clean +publishLocal
.PHONY: default

VERSION = $(shell sbt "print cucumberScala/version" | tail -n 1)
NEW_VERSION = $(subst -SNAPSHOT,,$(VERSION))
CURRENT_BRANCH = $(shell git rev-parse --abbrev-ref HEAD)

clean:
	sbt clean
.PHONY: clean

version:
	@echo ""
	@echo "The next version of Cucumber-Scala will be $(NEW_VERSION) and released from '$(CURRENT_BRANCH)'"
	@echo ""
.PHONY: version

update-installdoc:
	cat docs/install.md | ./scripts/update-install-doc.sh $(NEW_VERSION) > docs/install.md.tmp
	mv docs/install.md.tmp docs/install.md
.PHONY: update-installdoc

update-changelog:
	cat CHANGELOG.md | ./scripts/update-changelog.sh $(NEW_VERSION) > CHANGELOG.md.tmp
	mv CHANGELOG.md.tmp CHANGELOG.md
.PHONY: update-changelog

.commit-and-push-changelog-and-docs:
	git commit -am "Update CHANGELOG and docs for v$(NEW_VERSION)"
	git push
.PHONY: .commit-and-push-changelog

.configure-cukebot-in-docker:
	[ -f '/root/configure' ] && /root/configure
.PHONY: .configure-cukebot-in-docker