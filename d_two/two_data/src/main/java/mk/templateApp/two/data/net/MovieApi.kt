package mk.templateApp.two.data.net

import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import mk.templateApp.commonData.di.ClientProvider
import mk.templateApp.two.data.dto.MovieDTO
import javax.inject.Inject

interface MovieApi {

    suspend fun getMovies(): List<MovieDTO>

}

class MovieApiImpl @Inject constructor(private val clientProvider: ClientProvider) : MovieApi {
    override suspend fun getMovies(): List<MovieDTO> {
//        todo add transformation/ serializer for ktor
        val json = clientProvider().get {
            url("https://my-json-server.typicode.com/typicode/demo/posts")
        }.bodyAsText()
        return Json.decodeFromString(json)

    }
}
