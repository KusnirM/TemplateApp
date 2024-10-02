package mk.templateApp

import android.content.Context
import dagger.android.AndroidInjector
import mk.templateApp.commonDomain.dynamicFeature.FeatureType
import mk.templateApp.di.ApplicationComponent
import mk.templateApp.di.DaggerApplicationComponent
import mk.templateApp.di.DaggerSplitApplication
import mk.templateApp.di.BaseModuleInjector
import mk.templateApp.di.modules.ApplicationModule
import mk.templateApp.dynamicFeature.FeatureLoaderImpl
import mk.templateApp.presenter.dynamicDelivery.FeatureCache
import mk.templateApp.presenter.dynamicDelivery.FeatureCacheProvider
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class AndroidApp : DaggerSplitApplication(), FeatureCacheProvider {

    @Inject
    lateinit var featureLoader: FeatureLoaderImpl

    private var _applicationComponent: ApplicationComponent? = null

    var applicationComponent: ApplicationComponent
        get() {
            if (_applicationComponent == null) {
                _applicationComponent = DaggerApplicationComponent
                    .builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
            }
            return _applicationComponent!!
        }
        private set(comp) {
            _applicationComponent = comp
        }

    override fun applicationInjector(): AndroidInjector<AndroidApp> = applicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeLogging()
    }

    private fun initializeLogging() {
        Timber.plant(DebugTree())
    }

    fun addModuleInjector(featureType: FeatureType): BaseModuleInjector? =
        if (moduleInjectors.containsKey(featureType)) {
            moduleInjectors[featureType]
        } else {
            try {
                val clazz = Class.forName(featureType.injectorClassName)
                val injector = clazz.getDeclaredConstructor().newInstance() as BaseModuleInjector
                injector.inject(this)

                moduleActivityInjectors.add(injector.activityInjector())
                moduleFragmentInjectors.add(injector.fragmentInjector())
                moduleInjectors[featureType] = injector
                injector
            } catch (e: ClassNotFoundException) {
                println(e)
                null
            } catch (e: IllegalAccessException) {
                println(e)
                null
            } catch (e: InstantiationException) {
                println(e)
                null
            }
        }

    companion object {
        operator fun get(context: Context): AndroidApp = context.applicationContext as AndroidApp
    }

    override fun provideFeatureCache(): FeatureCache = featureLoader
}

