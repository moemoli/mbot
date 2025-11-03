plugins {
    kotlin("jvm") version "2.2.20"
    kotlin("kapt") version "2.2.20"
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.5.7" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects{
    apply {
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }


    group = "moe.imoli.mbot"
    extra["springCloudVersion"] = "2025.0.0"
    extra["springAlibabaCloudVersion"] = "2025.0.0.0"

    repositories {
        mavenCentral()
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
