package mk.templateApp.two.domain.interactor

import kotlinx.coroutines.flow.Flow
import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.domain.repository.MoviesRepository

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies

}
