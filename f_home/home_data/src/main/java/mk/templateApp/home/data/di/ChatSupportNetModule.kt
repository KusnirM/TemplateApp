//package mk.templateApp.a.data.di
//
//import com.google.gson.Gson
//import com.vhi.chatSupport.data.datasource.net.AApi
//import dagger.Module
//import dagger.Provides
//import ie.vhi.commonData.dagger.qualifiers.VHINetAuthenticated
//import data.net.TokenInterceptor
//import ie.vhi.commonDomain.service.environment.EnvironmentService
//import okhttp3.Cache
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import javax.inject.Named
//
//@Module
//object ANetModule {
//
//    @Provides
//    internal fun provideAApi(
//        @VHINetAuthenticated retrofit: Retrofit,
//    ): AApi = retrofit.create(AApi::class.java)
//
//    @Provides
//    fun provideOkHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//        cache: Cache,
//        tokenInterceptor: TokenInterceptor,
//    ): OkHttpClient {
//        val builder = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(tokenInterceptor)
//            .cache(cache)
//        return builder.build()
//    }
//
//    @Provides
//    @Named(CHAT_API_URI)
//    fun provideChatApiUri(environmentService: EnvironmentService): String =
//        environmentService.preferredEnvironment.baseUri
//
//    @Provides
//    fun provideGson(): Gson = Gson()
//}
