package mk.templateApp.two.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import mk.templateApp.commonData.di.scopes.ModuleScope
import mk.templateApp.di.ApplicationComponent
import mk.templateApp.two.FeatureImpl
import mk.templateApp.two.data.di.TwoDataModule
import mk.templateApp.two.ui.movieList.MovieListComponent

@ModuleScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [
        Bindings::class,
        AndroidInjectionModule::class,
        TwoDataModule::class
    ]
)
internal interface FeatureComponent {

    fun inject(injector: FeatureInjector)
    fun inject(feature: FeatureImpl)

    @Component.Builder
    interface Builder {
        fun applicationComponent(appComp: ApplicationComponent): Builder
        fun build(): FeatureComponent
    }
    fun movieListComponentBuilder() :MovieListComponent.Builder
}
