import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java-library") // Apply the java-library plugin for API and implementation separation.
    alias(libs.plugins.kotlin.jvm) // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    alias(libs.plugins.maven.publish)
}

allprojects {
    apply {
        plugin("java-library") // Java，可使用api方式导包
        plugin("org.jetbrains.kotlin.jvm") // Kotlin
    }

    group = "cn.starrys.tool"
    version = "0.1-SNAPSHOT"
    description = "星空工具"

    sourceSets {
        main {
            java.srcDirs("src/main/java", "src/main/kotlin")
            kotlin.srcDirs("src/main/java", "src/main/kotlin")
        }
        test {
            java.srcDirs("src/test/java", "src/test/kotlin")
            kotlin.srcDirs("src/test/java", "src/test/kotlin")
        }
    }

    kotlin {
        // Apply a specific Java toolchain to ease working on different environments.
        jvmToolchain(17)
        compilerOptions {
            freeCompilerArgs.addAll(
                "-Xjsr305=strict",
                // 强制所有Kotlin接口的默认方法生成Java8的default方法
                "-Xjvm-default=all",
            )
        }
    }

    configurations.compileOnly { extendsFrom(configurations.annotationProcessor.get()) }

    tasks.clean { delete("${projectDir}/out") }
    tasks.named<Test>("test") { useJUnitPlatform() } // Use JUnit Platform for unit tests.
}

subprojects {
    apply {
        plugin("com.vanniktech.maven.publish")
    }

    dependencies {
        implementation(rootProject.libs.logback)

        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    }

    // Maven 发布
    mavenPublishing {
        // 发布到 Maven Central
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, false)
        // 签名所有出版物
        signAllPublications()
        pom {
            // eg: name = "星空的工具包"（此 name 非artifactId）
            name = "${project.group}:${project.name}"
            // 描述
            description = project.description
            // 项目主页
            url = "https://gitee.com/XingKong746/starry-tool-project"
            licenses {
                license {
                    name = "Apache License, Version 2.0"
                    url = "https://gitee.com/XingKong746/starry-tool-project/blob/main/LICENSE"
                }
            }
            developers {
                developer {
                    name = "XingKong746"
                    email = "xkhi@qq.com"
                    url = "https://gitee.com/XingKong746"
                }
            }
            scm {
                // 版本控制仓库
                url = "https://gitee.com/XingKong746/starry-tool-project.git"
                connection = "scm:git:git://gitee.com/XingKong746/starry-tool-project.git"
                developerConnection = "scm:git:ssh://gitee.com/XingKong746/starry-tool-project.git"
            }
        }
    }
}


// 范围条件：为特定的项目排除一些配置
val excludes = arrayOf("bom", "......")

/**
 * 检查字符串是否不以指定的任一后缀结尾
 *
 * @param mainString 待检查的主字符串
 * @param endStrings 后缀数组，用于检查主字符串是否以这些后缀中的任一后缀结尾
 * @return 如果主字符串不以任何指定的后缀结尾，则返回true；否则返回false
 */
fun notEndsWith(mainString: String, endStrings: Array<String>): Boolean {
    // 初始化状态为true，表示主字符串不以后缀数组中的任一后缀结尾
    var status = true
    // 遍历后缀数组
    for (endStr in endStrings) {
        // 如果主字符串以后缀数组中的某个后缀结尾，将状态设置为false，并终止遍历
        if (mainString.endsWith(endStr)) {
            status = false
            break
        }
    }
    // 返回最终状态
    return status
}

