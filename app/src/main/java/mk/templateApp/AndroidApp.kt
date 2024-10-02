package mk.templateApp

import android.content.ComponentCallbacks2
import android.content.Context
import dagger.android.AndroidInjector
import mk.templateApp.commonDomain.dynamicDelivery.FeatureType
import mk.templateApp.commonPresenter.dynamicDelivery.FeatureCache
import mk.templateApp.commonPresenter.dynamicDelivery.FeatureCacheProvider
import mk.templateApp.di.ApplicationComponent
import mk.templateApp.di.DaggerApplicationComponent
import mk.templateApp.di.DaggerSplitApplication
import mk.templateApp.di.dynamic.BaseModuleInjector
import mk.templateApp.di.modules.ApplicationModule
import mk.templateApp.dynamicDelivery.FeatureLoaderImpl
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

//    lateinit var activityLifecycle: AppActivityLifeCycleCallbacks
//
//    private lateinit var applicationLifeCycle: VhiApplicationLifecycle
//
//    val lifecycle: ApplicationLifeCycle
//        get() = applicationLifeCycle

    override fun applicationInjector(): AndroidInjector<AndroidApp> = applicationComponent

    override fun onCreate() {
//        activityLifecycle = AppActivityLifeCycleCallbacks()
//        applicationLifeCycle = VhiApplicationLifecycle(activityLifecycle)
        super.onCreate()
//            registerActivityLifecycleCallbacks(activityLifecycle)

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
                Timber.e(e)
                null
            } catch (e: IllegalAccessException) {
                Timber.e(e)
                null
            } catch (e: InstantiationException) {
                Timber.e(e)
                null
            }
        }

    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
//            applicationLifeCycle.backgrounded()
        }
        super.onTrimMemory(level)
    }

    companion object {
        operator fun get(context: Context): AndroidApp = context.applicationContext as AndroidApp
    }

    override fun provideFeatureCache(): FeatureCache = featureLoader
}
