package mk.templateApp.two.domain.interactor

import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.commonDomain.usecases.base.UseCase
import mk.templateApp.two.domain.repository.MoviesRepository

class SetFavouriteMovieUseCase(
    private val moviesRepository: MoviesRepository,
    dispatcherProvider: DomainCoroutineDispatcherProvider,
) : UseCase<Int, Unit>(dispatcherProvider.getIO()) {

    override suspend fun run(params: Int) {
        moviesRepository.setFavouriteMovie(params)
    }
}

