plugins {
    kotlin("kapt")
    alias(libs.plugins.kotlin.plugin.spring)
}

version = "1.0.0"
description = "星空邮件工具 spring boot starter"

dependencies {
    api(project(":starry-mail"))
    implementation(kotlin("reflect"))
    implementation(libs.spring.boot.autoconfigure)
    kapt(libs.spring.boot.configuration.processor)
}
