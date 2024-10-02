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

    bundle {
        language {
            enableSplit = true
        }
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    buildTypes {
        val proguardRulesList = listOf(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro",
            "retrofit-proguard-rules.pro",
            "crashlytics-proguard-rules.pro",
            "glide-proguard-rules.pro",
            "joda-time-proguard-rules.pro",
            "moshi-proguard-rules.pro",
            "okhttp-proguard-rules.pro",
            "hypr-proguard-rules.pro",
            "stream-proguard-rules.pro",
            "navigation-proguard-rules.pro",
        )

        release {
            isMinifyEnabled = true
            setProguardFiles(proguardRulesList)
        }
//
//        alpha {
//            isMinifyEnabled = true
//            setProguardFiles(proguardRulesList)
//        }

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

    dynamicFeatures += setOf(":f_home")
}

dependencies {
    implementation(project(":l_common_presenter"))
    implementation(project(":l_common_domain"))
    implementation(project(":l_common_data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.junit)
    debugImplementation(libs.compose.ui.tooling)
}