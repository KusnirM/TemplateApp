package mk.templateApp.two.data.net

import mk.templateApp.two.data.dto.MovieDTO
import javax.inject.Inject

interface MoviesClient {
    suspend fun getMovies(): List<MovieDTO>
}

class MoviesClientImpl @Inject constructor(
    private val api: MovieApi
) : MoviesClient {
    override suspend fun getMovies(): List<MovieDTO> {
        return api.getMovies()
    }
}
