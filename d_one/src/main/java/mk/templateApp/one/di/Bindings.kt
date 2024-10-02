package mk.templateApp.one.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.one.ui.home.HomeModule
import mk.templateApp.one.ui.home.HomeScope
import mk.templateApp.one.ui.home.HomeView

@Module(includes = [ViewModelModule::class])
internal abstract class Bindings {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeView(): HomeView
}