import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.android.dynamic.feature)
}
apply(from = "$rootDir/config/buildsystem/presentation_build.gradle")

android {
    namespace = "mk.templateApp.one"
}
