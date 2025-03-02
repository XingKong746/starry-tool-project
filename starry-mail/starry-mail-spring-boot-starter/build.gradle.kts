plugins {
    kotlin("kapt")
    alias(libs.plugins.kotlin.plugin.spring)
}

version = "0.0.1"
description = "星空邮件工具 spring boot starter"

dependencies {
    api(project(":starry-mail"))
    implementation(libs.spring.boot.autoconfigure)
    kapt(libs.spring.boot.configuration.processor)
    implementation(kotlin("reflect"))
}
