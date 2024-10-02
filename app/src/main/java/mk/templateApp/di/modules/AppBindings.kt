package mk.templateApp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.ui.MainActivity
import mk.templateApp.ui.MainActivityModule
import mk.templateApp.ui.MainActivityScope

@Module
abstract class AppBindings {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity
}
