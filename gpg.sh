#! /bin/sh

gpg --passphrase "${GPG_SIGNING_KEY_PASSPHRASE}" --pinentry-mode loopback $@