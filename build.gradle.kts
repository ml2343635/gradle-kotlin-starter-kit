import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.cloud.tools.jib") version "3.3.2" apply false
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.8.20"
}

allprojects {
    group = "com.monstarlab"
    version = "0.1"
}

java.sourceCompatibility = JavaVersion.VERSION_17

configurations { compileOnly { extendsFrom(configurations.annotationProcessor.get()) } }

configurations { compileOnly { extendsFrom(configurations.annotationProcessor.get()) } }

repositories { mavenCentral() }

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "jacoco")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17

    repositories { mavenCentral() }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }

        dependencies {
            dependency("mysql:mysql-connector-java:8.0.32")
            dependencySet("io.jsonwebtoken:0.11.5") {
                entry("jjwt-api")
                entry("jjwt-impl")
                entry("jjwt-jackson")
            }
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
            dependency("io.swagger.core.v3:swagger-annotations-jakarta:2.2.9")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
            dependency("com.willowtreeapps.assertk:assertk-jvm:0.26.1")
            dependencySet("com.squareup.retrofit2:2.9.0") {
                entry("retrofit")
                entry("converter-jackson")
                entry("converter-scalars")
            }
            dependency("com.squareup.okhttp3:logging-interceptor:4.11.0")
            dependency("commons-validator:commons-validator:1.7")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}
