pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

apply(from = "${rootDir}/d_one/one.gradle")
apply(from = "${rootDir}/d_two/two.gradle")
include(
    ":app",

    ":common_data",
    ":common_domain",
    ":common_presenter",
    ":common_test",
    )
