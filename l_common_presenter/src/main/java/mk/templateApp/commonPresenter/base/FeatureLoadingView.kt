package mk.templateApp.commonPresenter.base

import android.content.Context
import mk.templateApp.commonPresenter.dynamicDelivery.FeatureCacheProvider
import mk.templateApp.commonDomain.dynamicDelivery.FeatureType

interface FeatureLoadingView {

    val featureType: FeatureType?

    fun loadFeature(context: Context) {
        featureType?.let {
            (context.applicationContext as FeatureCacheProvider).provideFeatureCache().getFeature(it)
        }
    }

    object Home : FeatureLoadingView {
        override val featureType: FeatureType = FeatureType.F_HOME
    }
}
