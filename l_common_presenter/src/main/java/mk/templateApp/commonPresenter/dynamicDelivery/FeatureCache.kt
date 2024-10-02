package mk.templateApp.commonPresenter.dynamicDelivery

import mk.templateApp.commonDomain.dynamicDelivery.FeatureType

interface FeatureCache {
    fun getFeature(feature: FeatureType): Feature
}
