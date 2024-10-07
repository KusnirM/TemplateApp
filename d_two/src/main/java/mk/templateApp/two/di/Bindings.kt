package mk.templateApp.two.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.two.ui.caching.CachingComponent
import mk.templateApp.two.ui.dynamic.DynamicActivity
import mk.templateApp.two.ui.dynamic.DynamicActivityModule
import mk.templateApp.two.ui.dynamic.DynamicActivityScope

@Module(
    subcomponents = [
        CachingComponent::class
    ]
)
internal interface Bindings {

    @DynamicActivityScope
    @ContributesAndroidInjector(modules = [DynamicActivityModule::class])
    fun dynamicActivity(): DynamicActivity
}
