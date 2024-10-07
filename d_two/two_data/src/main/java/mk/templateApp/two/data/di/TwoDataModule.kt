package mk.templateApp.two.data.di

import dagger.Binds
import dagger.Module
import mk.templateApp.commonData.di.scopes.ModuleScope
import mk.templateApp.two.data.repository.MoviesRepositoryImpl
import mk.templateApp.two.domain.repository.MoviesRepository

@Module
abstract class TwoDataModule {

    @ModuleScope
    @Binds
    internal abstract fun bindMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository

}
