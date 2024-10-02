package mk.templateApp.two.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.two.ui.dynamic.DynamicActivity
import mk.templateApp.two.ui.dynamic.DynamicActivityScope
import mk.templateApp.two.ui.dynamic.DynamicActivityModule

@Module
internal abstract class Bindings {

    @DynamicActivityScope
    @ContributesAndroidInjector(modules = [DynamicActivityModule::class])
    abstract fun dynamicActivity(): DynamicActivity
}
