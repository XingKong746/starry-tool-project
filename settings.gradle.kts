// For more detailed information on multi-project builds, please refer to https://docs.gradle.org/8.12.1/userguide/multi_project_builds.html in the Gradle documentation.

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage") repositories {
        // Use Maven Central as the default repository (where Gradle will download dependencies) in all subprojects.
        mavenLocal()
        maven { name = "TencentMavenMirror"; setUrl("https://mirrors.tencent.com/maven") }
        maven { name = "AliyunGoogleMirror"; setUrl("https://maven.aliyun.com/repository/google") }
        mavenCentral()
        google()
    }
}

pluginManagement.repositories {
    maven { name = "Aliyun Gradle Plugin Mirror"; setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
    gradlePluginPortal()
}

plugins {
// Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "starry-tool-project"

include("starry-core")
include("starry-tool", "starry-tool:starry-tool-spring-boot-starter")
include("starry-mail", "starry-mail:starry-mail-spring-boot-starter")

