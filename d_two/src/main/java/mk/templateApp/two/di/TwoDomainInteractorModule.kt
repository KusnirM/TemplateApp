package mk.templateApp.two.di

import dagger.Module
import dagger.Provides
import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.two.domain.interactor.GetMoviesUseCase
import mk.templateApp.two.domain.repository.MoviesRepository

@Module
class TwoDomainInteractorModule {

    @Provides
    fun provideGetMoviesUseCase(
        moviesRepository: MoviesRepository,
        dispatcherProvider: DomainCoroutineDispatcherProvider
    ): GetMoviesUseCase = GetMoviesUseCase(moviesRepository,dispatcherProvider)

}
