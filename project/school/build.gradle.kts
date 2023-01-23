import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val versionLogbackEncoder = "7.2"
val mockkVersion = "1.13.3"
val assertjCoreVersion = "3.24.2"

plugins {
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("jacoco")
}

group = "com.edu"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("net.logstash.logback:logstash-logback-encoder:$versionLogbackEncoder")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.liquibase:liquibase-core")
	implementation("org.springframework:spring-jdbc")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("org.postgresql:r2dbc-postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "org.mockito")
	}
	testImplementation("org.assertj:assertj-core:${assertjCoreVersion}")
	testImplementation("io.mockk:mockk:${mockkVersion}")

	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	outputs.upToDateWhen { false } // show CreateTeacherTest > execute success() PASSED
	// ref: https://docs.gradle.org/current/userguide/performance.html#suggestions_for_java_projects
	maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1

	testLogging {
		events("passed", "skipped", "failed")
		exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
	}
}


/**
 * @JacocoTestCoverage
 *
 * credits: -
 * - https://reflectoring.io/jacoco/
 * - https://github.com/gradle/kotlin-dsl/blob/master/samples/code-quality/build.gradle.kts
 * - https://docs.gradle.org/current/userguide/jacoco_plugin.html
 */

jacoco {
	toolVersion = "0.8.8"
}
tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("reports/coverage"))
	}
}
tasks.withType<JacocoReport> {
	classDirectories.setFrom(
		sourceSets.main.get().output.asFileTree.matching {
			include("**/domain/**")
		}
	)
}
// Running when `./gradlew test`
tasks.test {
	finalizedBy("jacocoTestReport")
	doLast {
		println("View code coverage at:")
		println("file://$buildDir/reports/coverage/index.html")
		println("View test results at:")
		println("file://$buildDir/reports/tests/test/index.html")
	}
}
