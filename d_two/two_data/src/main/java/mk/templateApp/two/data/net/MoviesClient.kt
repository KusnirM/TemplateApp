package mk.templateApp.two.data.net

import mk.templateApp.two.data.dto.MovieDTO
import javax.inject.Inject

interface MoviesClient {
    suspend fun getMovies(): List<MovieDTO>
}

class MoviesClientImpl @Inject constructor() : MoviesClient {
    override suspend fun getMovies(): List<MovieDTO> {
        return listOf(
            MovieDTO(0, "Home Alone"),
            MovieDTO(1, "Lenka in  WonderLand"),
            MovieDTO(2, "Inception"),
        )
    }
}
