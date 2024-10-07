package mk.templateApp.two.ui.caching

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import mk.templateApp.presenter.base.ObserveEvent
import mk.templateApp.presenter.components.Item
import mk.templateApp.presenter.components.spacers.ColumnSpacer.Spacer32
import mk.templateApp.presenter.components.text.TextTitleLarge
import mk.templateApp.presenter.theming.dp16
import mk.templateApp.two.di.ViewModelProvider
import mk.templateApp.two.ui.caching.CachingNavEvent.NavigateToThird
import mk.templateApp.two.ui.dynamic.Route.Caching


internal fun NavGraphBuilder.caching(navController: NavController) {
    composable<Caching> { backStackEntry ->
        val viewModel: CachingViewModel = ViewModelProvider.cachingViewModel()
        val route: Caching = backStackEntry.toRoute()
        val state: CachingState by viewModel.state.collectAsStateWithLifecycle()
        CachingScreen(state = state, thirdClick = viewModel::onThirdClicked)
        viewModel.loadInitialData(route)
        ObserveEvent(viewModel.navEvent) {
            when (it) {
                is NavigateToThird -> navController.navigate(it.route)
            }
        }
    }
}

@Composable
private fun CachingScreen(
    state: CachingState,
    thirdClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(dp16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextTitleLarge("Caching - ${state.args}")
        }
        Spacer32()

        when (state.loading) {
            true -> CircularProgressIndicator()
            false -> Item(
                title = "Third Screen",
                onClick = thirdClick
            )

            null -> Unit
        }
    }
}