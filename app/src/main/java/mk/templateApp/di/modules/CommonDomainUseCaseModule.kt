package mk.templateApp.di.modules

import dagger.Binds
import dagger.Module
import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.presenter.base.DomainCoroutineDispatcherProviderImpl

@Module
interface CommonDomainUseCaseModule {

    @Binds
    fun provideDomainCoroutineDispatcherProvider(
        impl: DomainCoroutineDispatcherProviderImpl
    ): DomainCoroutineDispatcherProvider
}
