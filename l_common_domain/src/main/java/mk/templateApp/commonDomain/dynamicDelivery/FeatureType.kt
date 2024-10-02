package mk.templateApp.commonDomain.dynamicDelivery

private const val PACKAGE_NAME = "mk.templateApp"

enum class FeatureType(
    val injectorClassName: String,
    val featureClassName: String,
) {

    F_HOME(
        injectorClassName = "$PACKAGE_NAME.home.di.HomeFeatureInjector",
        featureClassName = "$PACKAGE_NAME.home.HomeFeatureImpl"
    )
}