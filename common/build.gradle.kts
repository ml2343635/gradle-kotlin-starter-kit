plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.slf4j:slf4j-api")
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-web")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta")
    implementation("commons-validator:commons-validator")
    compileOnly("jakarta.servlet:jakarta.servlet-api")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm")
}