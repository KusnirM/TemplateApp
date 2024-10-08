package mk.templateApp.two.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import mk.templateApp.commonData.di.scopes.ModuleScope
import mk.templateApp.two.data.di.TwoDataModule.Companion.FLAG_PREFERENCES_NAME
import mk.templateApp.two.data.localStore.MovieLocalRepository
import mk.templateApp.two.data.localStore.MovieLocalRepositoryImpl
import mk.templateApp.two.data.net.MovieApi
import mk.templateApp.two.data.net.MovieApiImpl
import mk.templateApp.two.data.net.MoviesClient
import mk.templateApp.two.data.net.MoviesClientImpl
import mk.templateApp.two.data.repository.MoviesRepositoryImpl
import mk.templateApp.two.domain.repository.MoviesRepository

internal val Context.dataStore by preferencesDataStore(name = FLAG_PREFERENCES_NAME)

@Module
abstract class TwoDataModule {

    @ModuleScope
    @Binds
    internal abstract fun bindMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository

    @ModuleScope
    @Binds
    internal abstract fun bindMoviesClient(impl: MoviesClientImpl): MoviesClient

    @ModuleScope
    @Binds
    internal abstract fun movieApi(impl: MovieApiImpl): MovieApi

    companion object {

        @ModuleScope
        @Provides
        fun provideMovieLocalRepository(context: Context): MovieLocalRepository =
            MovieLocalRepositoryImpl(dataStore = context.dataStore)

        const val FLAG_PREFERENCES_NAME: String = "Movies"

    }
}
