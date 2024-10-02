package mk.templateApp.commonPresenter.di

import dagger.Binds
import dagger.Module
import mk.templateApp.commonDomain.base.DomainCoroutineDispatcherProvider
import mk.templateApp.commonPresenter.base.DomainCoroutineDispatcherProviderImpl

@Module
abstract class CommonDomainInteractorModule {

    @Binds
    abstract fun provideDomainCoroutineDispatcherProvider(
        domainCoroutineDispatcherProviderImpl: DomainCoroutineDispatcherProviderImpl,
    ): DomainCoroutineDispatcherProvider
}
