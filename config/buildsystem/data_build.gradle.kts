/**
 * Common Configuration for Dynamic Feature Data Module
 */

apply(from = "${rootDir}/config/buildsystem/data_common_build.gradle")

dependencies {
    implementation(project(":common_data"))
}
