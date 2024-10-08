package mk.templateApp.two.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import mk.templateApp.two.data.dto.MovieDTO
import mk.templateApp.two.data.localStore.MovieLocalRepository
import mk.templateApp.two.data.net.MoviesClient
import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val client: MoviesClient,
    private val localStore: MovieLocalRepository,
) : MoviesRepository {
    override val movies: Flow<List<Movie>> = allMovies()
        .combine(localStore.favMovies) { all: List<Movie>, fav: List<Int> ->
            all.map { it.copy(favourite = it.id in fav) }
        }

    private fun allMovies(): Flow<List<Movie>> {
        return flow {
            val movies = if (localStore.hasCachedMovies) {
                localStore.cachedMovies()!!
            } else {
                val movies = client.getMovies().map(MovieDTO::transform)
                localStore.cacheMovies(movies)
                movies
            }
            emit(movies)
        }
    }


    override suspend fun setFavouriteMovie(movie: Movie) {
        localStore.setFavMovie(movie)
    }
}
