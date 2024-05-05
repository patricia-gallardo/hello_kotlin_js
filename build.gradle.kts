import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    kotlin("multiplatform") version "1.9.23"
}

// This might not be a good idea, but I'm new at this
project.rootProject.plugins.withType(YarnPlugin::class.java) {
    with(project.rootProject.the<YarnRootExtension>()) {
        yarnLockMismatchReport = YarnLockMismatchReport.NONE
        yarnLockAutoReplace = true
//        reportNewYarnLock = false
    }
}

group = "hello_kotlin_js"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        nodejs {}
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.8.0")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-node:18.14.0-pre.504")
                implementation(npm("puppeteer", "> 22.7.0"))
            }
        }
        commonTest.dependencies {
            implementation(kotlin("test")) // Brings all the platform dependencies automatically
        }
    }
}
