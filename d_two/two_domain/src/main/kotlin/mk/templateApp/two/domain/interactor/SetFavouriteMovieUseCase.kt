package mk.templateApp.two.domain.interactor

import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.domain.repository.MoviesRepository

class SetFavouriteMovieUseCase(
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke(param: Movie) {
        moviesRepository.setFavouriteMovie(param)
    }
}

