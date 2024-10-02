plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
}

apply {
    from( "${rootProject.projectDir}/config/buildsystem/presentation_common_build.gradle")
}

android {
    namespace = "mk.templateApp"

    buildTypes {
        val proguardRulesList = listOf(
            getDefaultProguardFile("proguard-android-optimize.txt"),
        )

        release {
            isMinifyEnabled = true
            setProguardFiles(proguardRulesList)
        }

        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true

        }

        testOptions {
            animationsDisabled = true
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }
    dynamicFeatures += setOf(":d_one", ":d_two")
}

dependencies {
    implementation(project(":common_presenter"))
    implementation(project(":common_domain"))
    implementation(project(":common_data"))
    implementation(libs.androidx.core.ktx)

    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    implementation(platform(libs.compose.bom))

    implementation(libs.lifecycle.runtime.ktx)

    testImplementation(libs.junit)

}