package mk.templateApp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import mk.templateApp.commonData.di.scopes.AppScope
import mk.templateApp.commonDomain.dynamicFeature.FeatureLoader
import mk.templateApp.presenter.dynamicDelivery.FeatureCache
import mk.templateApp.dynamicFeature.FeatureLoaderImpl


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
    internal fun provideFeatureCache(impl: FeatureLoaderImpl): FeatureCache = impl
}
