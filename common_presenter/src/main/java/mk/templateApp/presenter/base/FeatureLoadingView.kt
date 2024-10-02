package mk.templateApp.presenter.base

import android.content.Context
import mk.templateApp.commonDomain.dynamicFeature.FeatureType
import mk.templateApp.presenter.dynamicDelivery.FeatureCacheProvider

interface FeatureLoadingView {

    val featureType: FeatureType?

    fun loadFeature(context: Context) {
        featureType?.let {
            (context.applicationContext as FeatureCacheProvider).provideFeatureCache().getFeature(it)
        }
    }

    object One : FeatureLoadingView {
        override val featureType: FeatureType = FeatureType.D_ONE
    }

    object Two : FeatureLoadingView {
        override val featureType: FeatureType = FeatureType.D_TWO
    }
}
