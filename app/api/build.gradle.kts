import com.google.cloud.tools.jib.api.buildplan.ImageFormat.OCI

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.google.cloud.tools.jib")
	kotlin("jvm")
	kotlin("plugin.spring")
}

jib {
	from {
		image = "adoptopenjdk/openjdk11:jre-11.0.11_9-ubi-minimal"
	}
	to {
		image = "starter/starter-api"
	}
	container {
		ports = listOf("8090", "30090")
		format = OCI
	}
}

dependencies {
	api(project(":service"))
	implementation(kotlin("stdlib"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm")
}
