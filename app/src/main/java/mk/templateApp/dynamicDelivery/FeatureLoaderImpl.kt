package mk.templateApp.dynamicDelivery

import android.content.Context
import mk.templateApp.commonPresenter.dynamicDelivery.Feature
import mk.templateApp.commonPresenter.dynamicDelivery.FeatureCache
import mk.templateApp.AndroidApp
import mk.templateApp.commonData.di.scopes.AppScope
import mk.templateApp.commonDomain.dynamicDelivery.FeatureLoader
import mk.templateApp.di.dynamic.BaseModuleInjector
import mk.templateApp.commonDomain.dynamicDelivery.FeatureType
import mk.templateApp.domain.exceptions.FailedToInitializeFeatureException
import timber.log.Timber
import javax.inject.Inject

@AppScope
class FeatureLoaderImpl @Inject constructor(private val context: Context) : FeatureLoader, FeatureCache {

    private val availableFeatures: MutableMap<FeatureType, Feature> = mutableMapOf()

    override suspend fun clearFeaturesCache() {
        for (feature in availableFeatures.values) {
            feature.clearUserData()
        }
    }

    override fun getFeature(feature: FeatureType): Feature = initFeature(feature)

    private fun initFeature(feature: FeatureType): Feature {
        if (availableFeatures.containsKey(feature)) {
            return availableFeatures[feature]!!
        }

        val injector = (context as AndroidApp).addModuleInjector(feature)
        loadAndInjectFeature(feature, injector)
        return availableFeatures[feature]!!
    }

    private fun loadAndInjectFeature(feature: FeatureType, injector: BaseModuleInjector?) {
        var exception: Throwable? = null
        if (injector == null) {
            exception = FailedToInitializeFeatureException("${feature.name} feature injector not found", null)
        } else {
            try {
                val featureClass = Class
                    .forName(feature.featureClassName)
                    .getDeclaredConstructor()
                    .newInstance() as Feature
                injector.injectFeature(featureClass)
                addFeature(feature, featureClass)
            } catch (e: ClassNotFoundException) {
                Timber.e("====Feature: ${feature.name}  initialization failed as feature is not present====")
                exception = e
            } catch (e: IllegalAccessException) {
                Timber.e("====Feature: ${feature.name}  initialization failed as feature is not present====")
                exception = e
            } catch (e: InstantiationException) {
                Timber.e("====Feature: ${feature.name}  initialization failed as feature is not present====")
                exception = e
            }
            exception?.let {
                throw FailedToInitializeFeatureException("${feature.name} failed to initialize", exception)
            }
        }
    }

    private fun addFeature(featureType: FeatureType, feature: Feature) {
        if (availableFeatures.containsKey(featureType)) {
            Timber.i("====== Feature: %s already present, not adding ====", featureType.name)
            return
        }
        availableFeatures[featureType] = feature
    }
}
