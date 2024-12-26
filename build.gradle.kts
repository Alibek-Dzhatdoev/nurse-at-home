import org.gradle.api.JavaVersion.VERSION_21

plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.ali"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.2.0")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.2.0")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.2.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.2.0")
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.2.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.4.0")
    implementation("org.springframework.cloud:spring-cloud-starter")

    implementation("com.google.code.gson:gson:2.11.0")

    testImplementation("org.projectlombok:lombok:1.18.30")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    implementation("io.jsonwebtoken:jjwt-api:0.12.1")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.1")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.1")

    implementation("org.postgresql:postgresql:42.7.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("net.kaczmarzyk:specification-arg-resolver:3.1.0")
    implementation("org.flywaydb:flyway-core:11.1.0")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:11.1.0")

    implementation("io.github.dvgaba:easy-random-core:6.0.4")

    implementation("io.micrometer:micrometer-tracing-bridge-brave")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
