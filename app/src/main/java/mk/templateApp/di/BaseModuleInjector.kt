package mk.templateApp.di

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import mk.templateApp.AndroidApp
import mk.templateApp.presenter.dynamicDelivery.Feature

interface BaseModuleInjector {

    fun inject(app: AndroidApp)

    fun injectFeature(feature: Feature)

    fun activityInjector(): DispatchingAndroidInjector<Activity>

    fun fragmentInjector(): DispatchingAndroidInjector<Fragment>
}
