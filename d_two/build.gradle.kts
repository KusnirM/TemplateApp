plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}
apply(from = "$rootDir/config/buildsystem/presentation_build.gradle")

android {
    namespace = "mk.templateApp.two"
}

dependencies {
    implementation(project(":two_data"))
    implementation(project(":two_domain"))
}
