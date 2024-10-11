package mk.templateApp.commonDomain.dynamicFeature

interface FeatureLoader {
    suspend fun clearFeaturesCache()
}