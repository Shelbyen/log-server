plugins {
    kotlin("jvm") version "2.0.10"
    application
}

group = "funn.j2k"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "2.3.12"
    implementation("io.ktor:ktor-network:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC.2")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("funn.j2k.logserver.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}