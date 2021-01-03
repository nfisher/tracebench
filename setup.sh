#!/bin/bash -eu

BAZEL_VERSION=`cat .bazelversion`

if [ ! -f ~/bin/bazel ]; then
curl -fsSLO https://github.com/bazelbuild/bazel/releases/download/${BAZEL_VERSION}/bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh
chmod 755 bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh
./bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh --user
else
  echo "Bazel already present"
fi
