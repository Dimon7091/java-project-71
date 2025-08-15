plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    application
    kotlin("jvm") version "2.2.0"
    kotlin("kapt") version "2.2.0"
    checkstyle
    id("org.sonarqube") version "6.2.0.5505"
    jacoco
}

sonar {
    properties {
        property("sonar.projectKey", "Dimon7091_java-project-71")
        property("sonar.organization", "dmitry-gorbunov-linter")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

jacoco {
    toolVersion = "0.8.13"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("info.picocli:picocli:4.7.7")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.7")
    kapt("info.picocli:picocli-codegen:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // отчет зависит от тестов
    reports {
        xml.required.set(true)  // нужен XML-отчет для SonarQube
        html.required.set(true) // полезный HTML-отчет
        csv.required.set(false)
    }
    // если нужно, укажите дополнительные пути к исходникам и классам
}
application {
    mainClass = "hexlet.code.App"
}