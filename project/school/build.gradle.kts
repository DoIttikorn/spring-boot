import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val versionLogbackEncoder = "7.2"


plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.graalvm.buildtools.native") version "0.9.18"
//    id("org.liquibase.gradle") version "2.1.1"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
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
    maven { url = uri("https://plugins.gradle.org/m2/") }
}

dependencies {
    implementation("net.logstash.logback:logstash-logback-encoder:$versionLogbackEncoder")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.liquibase:liquibase-core")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.86.Final:osx-aarch_64")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")

//    liquibaseRuntime("org.liquibase:liquibase-core")
//    liquibaseRuntime("org.postgresql:postgresql")
//    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl")
//    liquibaseRuntime("ch.qos.logback:logback-core")
//    liquibaseRuntime("ch.qos.logback:logback-classic")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


/**
 * Liquibase Gradle Plugin by Kamil Seweryn
 *
 * usage:
 * ./gradlew dropAll update -Pcontexts=dummy
 */
//liquibase {
//    activities.register("main") {
//        val contexts: String = project.extra.properties["contexts"] as String ?: "default"
//        this.arguments = mapOf(
//            "changeLogFile" to "src/main/resources/db/db/db.db-master.yaml",
//            "url" to "jdbc:postgresql://localhost:5432/school",
//            "username" to "dodo",
//            "contexts" to contexts
//        )
//    }
//}
