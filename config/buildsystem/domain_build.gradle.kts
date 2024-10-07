/**
 * Common Configuration for Dynamic Feature Domain Module
 */

apply(from = "$rootDir/config/buildsystem/domain_common_build.gradle")

dependencies {
//    todo: 1. set on multiplatform
//    todo: 2. move kmp to separate repo
    implementation(project(":common_domain"))
}
