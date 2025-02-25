// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath(libs.kotlin.gradle)
        classpath(libs.navigation.safeargs)
    }
}

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.ksp).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.android.dynamic.feature).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)

}