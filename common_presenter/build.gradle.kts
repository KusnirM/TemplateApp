plugins {
    id("com.android.library")
}

apply(from = "$rootDir/config/buildsystem/presentation_common_build.gradle")

android {
    namespace = "mk.templateApp.presenter"
}

dependencies {
    implementation(project(":common_domain"))
    implementation(project(":common_data"))
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
}
