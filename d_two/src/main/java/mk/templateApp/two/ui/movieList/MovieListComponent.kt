package mk.templateApp.two.ui.movieList

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import mk.templateApp.presenter.base.ViewModelKey
import mk.templateApp.two.di.TwoDomainInteractorModule
import javax.inject.Scope

@MovieListScope
@Subcomponent(modules = [MovieListModule::class])
internal interface MovieListComponent {


    @Subcomponent.Builder
    interface Builder {

        fun build(): MovieListComponent
    }

    val viewModel: MovieListViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MovieListScope

@Module(includes = [TwoDomainInteractorModule::class])
internal interface MovieListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    @MovieListScope
    fun viewModel(viewModel: MovieListViewModel): ViewModel
}
