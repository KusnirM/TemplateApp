package mk.templateApp.two.domain.interactor

import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.commonDomain.usecases.base.UseCase
import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.domain.repository.MoviesRepository

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository,
    dispatcherProvider: DomainCoroutineDispatcherProvider,
) : UseCase<Unit, List<Movie>>(dispatcherProvider.getIO()) {

    override suspend fun run(params: Unit): List<Movie> = moviesRepository.getMovies()

}
