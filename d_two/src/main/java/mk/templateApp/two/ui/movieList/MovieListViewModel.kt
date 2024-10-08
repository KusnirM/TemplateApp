package mk.templateApp.two.ui.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mk.templateApp.two.domain.interactor.GetMoviesUseCase
import mk.templateApp.two.domain.interactor.SetFavouriteMovieUseCase
import mk.templateApp.two.domain.model.Movie
import javax.inject.Inject

@MovieListScope
internal class MovieListViewModel @Inject constructor(
    getMoviesUseCase: GetMoviesUseCase,
    private val setFavouriteMovieUseCase: SetFavouriteMovieUseCase,
) : ViewModel() {

    val movies = getMoviesUseCase()
        .map { MovieListState(movies = it, loading = false) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MovieListState()
        )


    fun onMovieClicked(movie: Movie) {
        viewModelScope.launch {
            setFavouriteMovieUseCase(movie)
        }

    }
}

data class MovieListState(
    val loading: Boolean = true,
    val movies: List<Movie> = emptyList(),
)
