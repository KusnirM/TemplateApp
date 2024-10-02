package mk.templateApp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.ui.main.MainActivity
import mk.templateApp.ui.main.MainActivityModule
import mk.templateApp.ui.main.MainActivityScope

@Module
abstract class Bindings {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity
}
