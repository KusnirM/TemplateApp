package mk.templateApp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import mk.templateApp.commonData.di.scopes.AppScope
import mk.templateApp.commonDomain.base.CoroutineDispatcherProvider
import mk.templateApp.commonDomain.dynamicDelivery.FeatureLoader
import mk.templateApp.commonPresenter.base.CoroutineDispatcherProviderImpl
import mk.templateApp.commonPresenter.di.CommonDomainInteractorModule
import mk.templateApp.commonPresenter.dynamicDelivery.FeatureCache
import mk.templateApp.dynamicDelivery.FeatureLoaderImpl

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @AppScope
    internal fun provideApplicationContext(): Context = application

    @Provides
    internal fun provideFeatureLoader(
        featureLoaderImpl: FeatureLoaderImpl,
    ): FeatureLoader = featureLoaderImpl

    @Provides
    internal fun provideFeatureCache(
        featureLoaderImpl: FeatureLoaderImpl,
    ): FeatureCache = featureLoaderImpl

    @Provides
    fun provideCoroutineDispatcherProviderImpl(
        coroutineDispatcherProviderImpl: CoroutineDispatcherProviderImpl,
    ): CoroutineDispatcherProvider = coroutineDispatcherProviderImpl
}
