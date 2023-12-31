import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	id("org.springframework.boot") version "2.7.17"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.9.10"
}

group = "com.kantarix"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven { url = uri("https://maven-other.tuya.com/repository/maven-public/") }
}

dependencies {
	/**
	 * Spring
	 */
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.kafka:spring-kafka")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	/**
	 * Tuya
	 */
	implementation("com.tuya:tuya-spring-boot-starter:1.3.2")

	/**
	 * Other
	 */
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("org.liquibase:liquibase-core:4.24.0")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

	implementation("org.springdoc:springdoc-openapi-kotlin:1.7.0")
	implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
	implementation("org.springdoc:springdoc-openapi-webmvc-core:1.7.0")
}

allOpen {
	annotations(
		"javax.persistence.Entity",
		"javax.persistence.MappedSuperclass",
		"javax.persistence.Embeddable"
	)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

sourceSets.main {
	java.srcDirs("src/main/kotlin")
}

tasks.withType<BootRun> {
	jvmArgs(
		"--add-opens=java.base/java.lang=ALL-UNNAMED",
		"--add-opens=java.management/sun.management=ALL-UNNAMED",
		"--add-opens=java.base/sun.net=ALL-UNNAMED",
	)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
