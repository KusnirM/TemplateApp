package mk.templateApp.commonDomain.dynamicFeature

private const val PACKAGE_NAME = "mk.templateApp"

enum class FeatureType(
    val injectorClassName: String,
    val featureClassName: String,
) {

    D_ONE(
        injectorClassName = "$PACKAGE_NAME.one.di.FeatureInjector",
        featureClassName = "$PACKAGE_NAME.one.FeatureImpl"
    ),
    D_TWO(
        injectorClassName = "$PACKAGE_NAME.two.di.FeatureInjector",
        featureClassName = "$PACKAGE_NAME.two.FeatureImpl"
    )
}