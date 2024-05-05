import org.gradle.internal.logging.text.StyledTextOutput
import org.gradle.internal.logging.text.StyledTextOutput.Style
import org.gradle.internal.logging.text.StyledTextOutputFactory
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

val out: StyledTextOutput = project.serviceOf<StyledTextOutputFactory>().create("output")

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
        useCommonJs()
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
                implementation("org.jetbrains.kotlin-wrappers:kotlin-node:20.11.30-pre.739")
                // ==========
                // Please note, Dukat integration in Gradle plugin does not work now.
                // It is in redesigning process.
                // ==========
                // 'npm(String, String, Boolean): Dependency' is deprecated. Dukat integration is in redesigning process. Now it does not work.
                // implementation(npm("axios", "1.6.8", generateExternals = true))
                implementation(npm("axios", "1.6.8"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test")) // Brings all the platform dependencies automatically
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test")) // Brings all the platform dependencies automatically
            }
        }
    }
}

tasks.withType<AbstractTestTask> {
    afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
        fun status(prefix: String, resultType: TestResult.ResultType, header: Boolean = false): StyledTextOutput {
            val progressStatus = if (header) Style.Header else Style.Info
            return when (resultType) {
                TestResult.ResultType.SUCCESS -> out.style(progressStatus).text(prefix).style(Style.Success).text(resultType)
                TestResult.ResultType.FAILURE -> out.style(progressStatus).text(prefix).style(Style.Failure).text(resultType)
                TestResult.ResultType.SKIPPED -> out.style(progressStatus).text(prefix).style(Style.Info).text(resultType)
            }
        }

        fun col(text: String) = text.padEnd(11, ' ')
        if (desc.parent == null) {
            status("${col("TEST RESULT")} : ", result.resultType, true).println()
        } else {
            status("${col(desc.displayName)} : ", result.resultType).println()
        }
    }))
}
