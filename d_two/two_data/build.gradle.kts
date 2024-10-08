import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("com.android.library")
}

apply(from = "$rootDir/config/buildsystem/data_build.gradle")

/**
 * Only configurations that are specific to this module should be added to this file.
 */

android {
    namespace = "mk.templateApp.two.data"
}
dependencies {
    implementation(project(":two_domain"))
}
