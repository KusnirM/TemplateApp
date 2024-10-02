package mk.templateApp.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import mk.templateApp.commonDomain.dynamicDelivery.FeatureType
import mk.templateApp.di.dynamic.BaseModuleInjector
import javax.inject.Inject

abstract class DaggerSplitApplication : SplitCompatApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    val moduleActivityInjectors = mutableListOf<DispatchingAndroidInjector<Activity>>()
    val moduleFragmentInjectors = mutableListOf<DispatchingAndroidInjector<Fragment>>()

    val moduleInjectors = mutableMapOf<FeatureType, BaseModuleInjector>()

    override fun onCreate() {
        super.onCreate()
        // inject needs AndroidInjector<DaggerSplitApplication> but applicationInjector()
        // returns AndroidInjector<out DaggerSplitApplication>
        @Suppress("UNCHECKED_CAST")
        val applicationInjector = applicationInjector() as AndroidInjector<DaggerSplitApplication>
        applicationInjector.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = AndroidInjector {
        if (androidInjector.maybeInject(it)) {
            return@AndroidInjector
        }
        when (it) {
            is Fragment -> {
                moduleFragmentInjectors.forEach { injector ->
                    if (injector.maybeInject(it)) {
                        return@AndroidInjector
                    }
                }
            }
            is Activity -> {
                moduleActivityInjectors.forEach { injector ->
                    if (injector.maybeInject(it)) {
                        return@AndroidInjector
                    }
                }
            }
            else -> {
                throw UnsupportedOperationException("Only Fragments and Activities are supported")
            }
        }
        throw IllegalStateException("Injector not found for $it")
    }

    /**
     * Implementations should return an [AndroidInjector] for the concrete [ ].
     * Typically, that injector is a [dagger.Component].
     */
    protected abstract fun applicationInjector(): AndroidInjector<out DaggerSplitApplication>
}
