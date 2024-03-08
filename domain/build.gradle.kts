plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    api(project(":common"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("noarg"))
    implementation(kotlin("allopen"))
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.security:spring-security-web")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}