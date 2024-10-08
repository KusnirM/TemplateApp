package mk.templateApp.di

import android.content.Context
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import mk.templateApp.AndroidApp
import mk.templateApp.commonData.di.CommonNetModule
import mk.templateApp.commonData.di.scopes.AppScope
import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.di.modules.AppBindings
import mk.templateApp.di.modules.ApplicationModule
import mk.templateApp.di.modules.CommonDomainUseCaseModule
import mk.templateApp.dynamicFeature.FeatureLoaderImpl
import mk.templateApp.presenter.dynamicDelivery.FeatureCache

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppBindings::class,
        ApplicationModule::class,
        CommonDomainUseCaseModule::class,
        CommonNetModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<AndroidApp> {

    override fun inject(app: AndroidApp)

    fun context(): Context

    fun provideFeatureCache(): FeatureCache

    fun featureLoaderImpl(): FeatureLoaderImpl

    fun domainCoroutineDispatcherProvider(): DomainCoroutineDispatcherProvider
}
