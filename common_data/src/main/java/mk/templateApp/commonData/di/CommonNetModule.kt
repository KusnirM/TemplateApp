package mk.templateApp.commonData.di

import dagger.Binds
import dagger.Module
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import timber.log.Timber
import javax.inject.Inject

private const val TIME_OUT = 60_000

@Module
abstract class CommonNetModule {

    @Binds
   abstract fun provideClientProvider(impl: ClientProvider): ClientProvider

}

class ClientProvider @Inject constructor() {

    operator fun invoke(): HttpClient = HttpClient(Android) {
//        install(Json) {
//            KotlinxSerializer(
//                Json {
//                    prettyPrint = true
//                    isLenient = true
//                    ignoreUnknownKeys = true
//                },
//            )
//            }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }

        //Logging
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.d("HttpLogging: $message")
                }

            }
        }

        //Http Response
        install(ResponseObserver) {
            onResponse { response ->
                Timber.d("HTTP status: ${response.status.value}")
            }
        }

        // Headers
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    }
}
