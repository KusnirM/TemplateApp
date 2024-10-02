package mk.templateApp.one.di

import android.app.Activity
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import mk.templateApp.AndroidApp
import mk.templateApp.presenter.dynamicDelivery.Feature
import mk.templateApp.di.BaseModuleInjector
import mk.templateApp.one.FeatureImpl
import javax.inject.Inject

@Keep
internal class FeatureInjector : BaseModuleInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: FeatureComponent

    override fun inject(app: AndroidApp) {
        component = DaggerFeatureComponent
            .builder()
            .applicationComponent(app.applicationComponent)
            .build()

        component.inject(injector = this)
    }

    override fun injectFeature(feature: Feature) {
        component.inject(feature = feature as FeatureImpl)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> = fragmentInjector
}
