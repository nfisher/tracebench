workspace(name="tracebench")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
http_archive(
    name = "rules_java",
    url = "https://github.com/bazelbuild/rules_java/releases/download/0.1.1/rules_java-0.1.1.tar.gz",
    sha256 = "220b87d8cfabd22d1c6d8e3cdb4249abd4c93dcc152e0667db061fb1b957ee68",
)
load("@rules_java//java:repositories.bzl", "rules_java_dependencies", "rules_java_toolchains")

rules_java_dependencies()
rules_java_toolchains()

RULES_JVM_EXTERNAL_TAG = "3.3"
RULES_JVM_EXTERNAL_SHA = "d85951a92c0908c80bd8551002d66cb23c3434409c814179c0ff026b53544dab"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

# http_archive(
#     name = "io_bazel_rules_docker",
#     sha256 = "1698624e878b0607052ae6131aa216d45ebb63871ec497f26c67455b34119c80",
#     strip_prefix = "rules_docker-0.15.0",
#     urls = ["https://github.com/bazelbuild/rules_docker/releases/download/v0.15.0/rules_docker-v0.15.0.tar.gz"],
# )

# load("@io_bazel_rules_docker//repositories:repositories.bzl", container_repositories = "repositories")
# container_repositories()

# load("@io_bazel_rules_docker//java:image.bzl", java_docker_image_repos = "repositories")
# java_docker_image_repos()

# load("@io_bazel_rules_docker//container:container.bzl", "container_pull")
# container_pull(
#   name = "java_base",
#   registry = "gcr.io",
#   repository = "distroless/java-debian10",
#   tag = "11",
#   digest = "sha256:9a7c9bebb9039bf5b6f665023368052f3208f7db990a470b9acbb1c4ba007a7b",
# )


SLF4J_VERSION="2.12.1"
JUNIT_VERSION="5.7.0"
HAMCREST_VERSION="2.2"
CASSANDRA_VERSION="3.10.2"
MSGPACK_VERSION="0.8.22"
DROPWIZARD_VERSION="2.0.17"

maven_install(
    # after updating dependencies run: `bazel run @unpinned_maven//:pin`
    artifacts = [
        maven.artifact("io.dropwizard", "dropwizard-core", DROPWIZARD_VERSION),
        maven.artifact("io.dropwizard", "dropwizard-http2", DROPWIZARD_VERSION),
        maven.artifact("io.dropwizard", "dropwizard-migrations", DROPWIZARD_VERSION),
        maven.artifact("io.dropwizard", "dropwizard-testing", DROPWIZARD_VERSION, exclusions = ["junit:junit"]),

        maven.artifact("com.jsoniter", "jsoniter", "0.9.23"),

        maven.artifact("com.datastax.cassandra", "cassandra-driver-core", CASSANDRA_VERSION),
        maven.artifact("com.datastax.cassandra", "cassandra-driver-mapping", CASSANDRA_VERSION),
        maven.artifact("com.datastax.cassandra", "cassandra-driver-extras", CASSANDRA_VERSION),

        maven.artifact("org.apache.logging.log4j", "log4j-slf4j-impl", SLF4J_VERSION),
        maven.artifact("org.apache.logging.log4j", "log4j-api", SLF4J_VERSION),
        maven.artifact("org.apache.logging.log4j", "log4j-core", SLF4J_VERSION),

        maven.artifact("com.fasterxml.jackson.core", "jackson-databind", "2.12.0"),
        maven.artifact("org.msgpack", "jackson-dataformat-msgpack", MSGPACK_VERSION),
        maven.artifact("org.msgpack", "msgpack-core", MSGPACK_VERSION),

        # test artifacts
        maven.artifact("org.junit.jupiter", "junit-jupiter", JUNIT_VERSION),
        maven.artifact("org.junit.jupiter", "junit-jupiter-engine", JUNIT_VERSION),
        maven.artifact("org.junit.jupiter", "junit-jupiter-params", JUNIT_VERSION),
        maven.artifact("org.junit.platform", "junit-platform-console", "1.7.0"),

        maven.artifact("org.hamcrest", "hamcrest", HAMCREST_VERSION),
        maven.artifact("org.hamcrest", "hamcrest-library", HAMCREST_VERSION),

        maven.artifact("org.mockito", "mockito-core", "3.6.28"),
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://repository.apache.org/content/repositories/snapshots/",
        "https://packages.confluent.io/maven",
    ],
    maven_install_json = "@tracebench//:maven_install.json",
)

load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()
