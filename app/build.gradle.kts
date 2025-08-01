plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    application
    kotlin("jvm") version "2.2.0"
    kotlin("kapt") version "2.2.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("info.picocli:picocli:4.7.7")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.7")
    kapt("info.picocli:picocli-codegen:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
}


tasks.test {
    useJUnitPlatform()
}
application {
    mainClass = "hexlet.code.App"
}

