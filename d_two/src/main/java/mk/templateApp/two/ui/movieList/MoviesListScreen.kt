package mk.templateApp.two.ui.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.templateApp.presenter.components.spacers.ColumnSpacer.Spacer16
import mk.templateApp.presenter.components.spacers.ColumnSpacer.Spacer32
import mk.templateApp.presenter.components.spacers.RowSpacer.Spacer4
import mk.templateApp.presenter.components.text.TextBodyLarge
import mk.templateApp.presenter.components.text.TextTitleLarge
import mk.templateApp.presenter.components.text.TextTitleLargePrimaryBold
import mk.templateApp.presenter.theming.cardElevation
import mk.templateApp.presenter.theming.dp16
import mk.templateApp.two.R
import mk.templateApp.two.di.ViewModelProvider
import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.ui.dynamic.Route.Caching


internal fun NavGraphBuilder.movieList() {
    composable<Caching> { backStackEntry ->
        val viewModel: MovieListViewModel = ViewModelProvider.movieListViewModel()
        val state: MovieListState by viewModel.movies.collectAsStateWithLifecycle()
        MoviesListScreen(
            state = state,
            movieClick = viewModel::onMovieClicked,
        )
    }
}

@Composable
private fun MoviesListScreen(
    state: MovieListState,
    movieClick: (Movie) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(dp16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextTitleLarge(stringResource(R.string.all_you_can_watch))
        }
        Spacer32()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dp16),
        ) {
            TextTitleLargePrimaryBold("Movies")
            if (state.loading) {
                Spacer32()
                CircularProgressIndicator()
            } else {
                MovieList(state.movies, movieClick)
            }
        }
    }
}

@Composable
private fun ColumnScope.MovieList(movies: List<Movie>, movieClick: (Movie) -> Unit) {
    movies.forEachIndexed { i, movie ->
        if (i == 0) {
            Spacer16()
        }
        Spacer16()
        Movie(movie) { favourite ->
            movieClick(movie.copy(favourite = favourite))
        }
    }
}

@Composable
private fun Movie(movie: Movie, onCheckedChange: (Boolean) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(cardElevation),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp16)
        ) {
            TextBodyLarge(modifier = Modifier.weight(1f), text = movie.name)
            Spacer4()
            Checkbox(
                checked = movie.favourite, onCheckedChange = onCheckedChange
            )
        }
    }
}
