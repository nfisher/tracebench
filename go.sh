#!/bin/sh -eu

bazel test --test_verbose_timeout_warnings --verbose_explanations --test_summary=detailed --test_output=all //...
