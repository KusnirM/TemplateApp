package mk.templateApp.two.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mk.templateApp.two.ui.movieList.MovieListComponent
import mk.templateApp.two.ui.dynamic.DynamicActivity
import mk.templateApp.two.ui.dynamic.DynamicActivityModule
import mk.templateApp.two.ui.dynamic.DynamicActivityScope

@Module(
    subcomponents = [
        MovieListComponent::class
    ]
)
internal interface Bindings {

    @DynamicActivityScope
    @ContributesAndroidInjector(modules = [DynamicActivityModule::class])
    fun dynamicActivity(): DynamicActivity
}
