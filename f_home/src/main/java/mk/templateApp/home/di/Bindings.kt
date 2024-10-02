package mk.templateApp.home.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.home.features.dashboard.DashboardModule
import mk.templateApp.home.features.dashboard.DashboardScope
import mk.templateApp.home.features.dashboard.DashboardView

@Module(includes = [ViewModelModule::class])
abstract class Bindings {

    @DashboardScope
    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun dashboardView(): DashboardView
}