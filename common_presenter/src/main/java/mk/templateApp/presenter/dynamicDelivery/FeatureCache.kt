package mk.templateApp.presenter.dynamicDelivery

import mk.templateApp.commonDomain.dynamicFeature.FeatureType

interface FeatureCache {
    fun getFeature(feature: FeatureType): Feature
}
