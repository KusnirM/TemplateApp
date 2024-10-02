package mk.templateApp.home.di

import android.app.Activity
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import mk.templateApp.AndroidApp
import mk.templateApp.commonPresenter.dynamicDelivery.Feature
import mk.templateApp.di.dynamic.BaseModuleInjector
import mk.templateApp.home.HomeFeatureImpl
import javax.inject.Inject

@Keep
class HomeFeatureInjector : BaseModuleInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: HomeFeatureComponent

    override fun inject(app: AndroidApp) {
        component = DaggerHomeFeatureComponent
            .builder()
            .applicationComponent(app.applicationComponent)
            .build()

        component.inject(injector = this)
    }

    override fun injectFeature(feature: Feature) {
        component.inject(feature = feature as HomeFeatureImpl)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> = fragmentInjector
}
