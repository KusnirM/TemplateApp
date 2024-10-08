package mk.templateApp.two.domain.repository

import kotlinx.coroutines.flow.Flow
import mk.templateApp.two.domain.model.Movie

interface MoviesRepository {
    val movies: Flow<List<Movie>>
    suspend fun setFavouriteMovie(movie: Movie)
}
