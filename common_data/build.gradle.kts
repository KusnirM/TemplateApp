import org.gradle.kotlin.dsl.android

plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}
apply(from = "$rootDir/config/buildsystem/data_common_build.gradle")

android {
    namespace = "mk.templateApp.data"
}

dependencies {
    implementation(libs.kotlin.ksp.api)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.timber)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.android.processor)
//    ksp(libs.dagger.hilt.compiler)

}


