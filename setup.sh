#!/bin/bash -eu

BAZEL_VERSION=`cat .bazelversion`

curl -fsSLO https://github.com/bazelbuild/bazel/releases/download/${BAZEL_VERSION}/bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh
chmod 755 bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh
./bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh --user
~/bin/bazel version
