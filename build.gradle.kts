plugins {
    kotlin("multiplatform") version "1.9.23"
    application
}

group = "hello_kotlin_js"
version = "1.0"

application {
    mainClass.set("AppKt")
}

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        nodejs {}
        binaries.executable()
    }

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    }

    sourceSets["jsMain"].dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
        implementation("org.jetbrains.kotlin-wrappers:kotlin-node:18.14.0-pre.504")
    }
}
