package mk.templateApp.commonData.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Lazy
import dagger.Module
import dagger.Provides
import mk.templateApp.commonData.di.qualifiers.VHINetAuthenticated
import mk.templateApp.commonData.di.scopes.AppScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class NetModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Timber.d(message) }
        return logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
    }


    @Provides
    @VHINetAuthenticated
    internal fun provideVHINetAuthenticatedOkHttpClient(
//        @VHINetAuthenticated headerInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
//            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(HTTP_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(HTTP_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @VHINetAuthenticated
    @AppScope
    internal fun provideVHINetAuthenticatedRetrofit(
        gson: Gson,
        @VHINetAuthenticated okHttpClient: Lazy<OkHttpClient>,
    ): Retrofit {
        return Retrofit.Builder()
            .callFactory { request -> okHttpClient.get().newCall(request) }
//            .baseUrl(BuildConfig.BUILD_TYPE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
