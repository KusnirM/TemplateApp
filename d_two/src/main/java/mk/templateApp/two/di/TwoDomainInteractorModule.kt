package mk.templateApp.two.di

import dagger.Module
import dagger.Provides
import mk.templateApp.two.domain.interactor.GetMoviesUseCase
import mk.templateApp.two.domain.interactor.SetFavouriteMovieUseCase
import mk.templateApp.two.domain.repository.MoviesRepository

@Module
class TwoDomainInteractorModule {

    @Provides
    fun provideGetMoviesUseCase(
        moviesRepository: MoviesRepository
    ): GetMoviesUseCase = GetMoviesUseCase(moviesRepository)

    @Provides
    fun provideSetFavouriteMovieUseCase(
        moviesRepository: MoviesRepository,
    ): SetFavouriteMovieUseCase = SetFavouriteMovieUseCase(moviesRepository)
}
