#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_1fba5115ab15_key -iv $encrypted_1fba5115ab15_iv -in continuous-deployment/codesigning.asc.enc -out continuous-deployment/codesigning.asc -d
    gpg -q --fast-import continuous-deployment/codesigning.asc
fi