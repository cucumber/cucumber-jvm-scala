#!/usr/bin/env bash
set -uf -o pipefail

# Reads installation doc from STDIN and writes out a new one to STDOUT where:
#
# * the version number is updated for both Maven and sbt
#

new_version=$1

installdoc=$(</dev/stdin)

# Maven
installdoc=$(echo "${installdoc}" | sed "s/<version>[0-9]\+.[0-9]\+.[0-9]\+<\/version>/<version>${new_version}<\/version>/g")
# sbt
installdoc=$(echo "${installdoc}" | sed "s/% \"[0-9]\+.[0-9]\+.[0-9]\+\" %/% \"${new_version}\" %/g")

# Output
echo "${installdoc}"