package mk.templateApp.di

import android.content.Context
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import mk.templateApp.AndroidApp
import mk.templateApp.commonData.di.scopes.AppScope
import mk.templateApp.commonDomain.base.CoroutineDispatcherProvider
import mk.templateApp.commonDomain.dynamicDelivery.FeatureLoader
import mk.templateApp.commonPresenter.di.CommonDomainInteractorModule
import mk.templateApp.commonPresenter.dynamicDelivery.FeatureCache
import mk.templateApp.di.modules.ApplicationModule
import mk.templateApp.di.modules.Bindings
import mk.templateApp.dynamicDelivery.FeatureLoaderImpl

/**
 * A component whose lifetime is the life of the application.
 */
@AppScope // Constraints this component to one-per-application or unscoped bindings.
@Component(
    modules = [
        AndroidInjectionModule::class,
        Bindings::class,
        ApplicationModule::class,
        CommonDomainInteractorModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<AndroidApp> {

    override fun inject(app: AndroidApp)

    fun context(): Context

    //
//    fun sharedPrefWrapper(): SharedPrefsWrapper
//
//    fun provideNetworkObserver(): NetworkObserver
//
    fun provideFeatureLoader(): FeatureLoader

    fun provideFeatureCache(): FeatureCache
//
//    @MainDependency
//    fun okHttpClient(): OkHttpClient
//
//    @Header
//    fun provideHeader(): Interceptor

//    fun provideFeatureNavigator(): FeatureNavigator

//    @VHINetAuthenticated
//    fun retrofitAuthenticated(): Retrofit
//
//    @VHINet
//    fun retrofit(): Retrofit

    //    fun loggingInterceptor(): HttpLoggingInterceptor
//
    fun coroutineDispatcherProvider(): CoroutineDispatcherProvider

    //
    fun featureLoaderImpl(): FeatureLoaderImpl
}
