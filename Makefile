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
	[ -f '/home/cukebot/configure' ] && /home/cukebot/configure
	# Cucumber team provides the passphrase in this variable
	export PGP_PASSPHRASE="$GPG_SIGNING_KEY_PASSPHRASE"
.PHONY: .configure-cukebot-in-docker

.release-in-docker: .configure-cukebot-in-docker default update-changelog update-installdoc .commit-and-push-changelog-and-docs
	sbt "release cross with-defaults"
.PHONY: release-in-docker

release:
	[ -d '../secrets' ]  || git clone keybase://team/cucumberbdd/secrets ../secrets
	git -C ../secrets reset HEAD --hard
	git -C ../secrets pull
	../secrets/update_permissions
	docker pull cucumber/cucumber-build:latest
	docker run \
	  --volume "${shell pwd}":/app \
 	  --volume "${shell pwd}/../secrets/configure":/home/cukebot/configure \
	  --volume "${shell pwd}/../secrets/codesigning.key":/home/cukebot/codesigning.key \
	  --volume "${shell pwd}/../secrets/gpg-with-passphrase":/home/cukebot/gpg-with-passphrase \
	  --volume "${shell pwd}/../secrets/.ssh":/home/cukebot/.ssh \
	  --volume "${HOME}/.ivy2":/home/cukebot/.ivy2 \
	  --volume "${HOME}/.cache/coursier":/home/cukebot/.cache/coursier \
	  --env-file "${shell pwd}/../secrets/secrets.list" \
	  --user 1000 \
	  --rm \
	  -it cucumber/cucumber-build:latest \
	  make .release-in-docker
.PHONY: release

