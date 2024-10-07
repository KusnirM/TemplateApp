package mk.templateApp.two.domain.repository

import mk.templateApp.two.domain.model.Movie

interface MoviesRepository {
    fun getMovies(): List<Movie>
    fun getFavouriteMovies(): List<Movie>
    fun setFavouriteMovie(id: Int)
}
