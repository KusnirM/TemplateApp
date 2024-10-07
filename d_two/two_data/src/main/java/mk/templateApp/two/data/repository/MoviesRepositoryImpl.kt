package mk.templateApp.two.data.repository

import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor() : MoviesRepository {
    override fun getMovies(): List<Movie> {
        return emptyList()
    }

    override fun getFavouriteMovies(): List<Movie> {
        return emptyList()
    }

    override fun setFavouriteMovie(id: Int) {
    }
}
