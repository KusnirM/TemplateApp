package mk.templateApp.home.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import mk.templateApp.commonData.di.scopes.ModuleScope
import mk.templateApp.di.ApplicationComponent
import mk.templateApp.home.HomeFeatureImpl

@ModuleScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [
        Bindings::class,
        AndroidInjectionModule::class,
    ]
)

interface HomeFeatureComponent {
    fun inject(injector: HomeFeatureInjector)
    fun inject(feature: HomeFeatureImpl)

    @Component.Builder
    interface Builder {
        fun applicationComponent(appComp: ApplicationComponent): Builder
        fun build(): HomeFeatureComponent
    }
}
