package mk.templateApp.two.ui.home

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
import mk.templateApp.presenter.base.ObserveEvent
import mk.templateApp.presenter.components.Item
import mk.templateApp.presenter.components.spacers.ColumnSpacer.Spacer32
import mk.templateApp.presenter.components.text.TextTitleLarge
import mk.templateApp.presenter.theming.dp16
import mk.templateApp.two.di.ViewModelProvider
import mk.templateApp.two.ui.dynamic.Route.Home
import mk.templateApp.two.ui.home.HomeNavEvent.NavigateToSecond

internal fun NavGraphBuilder.home(controller: NavController) {
    composable<Home> {
        val viewModel: HomeViewModel = ViewModelProvider.homeViewModel()
        viewModel.loadInitialData()
        HomeScreen(viewModel = viewModel)
        ObserveEvent(viewModel.navEvent) {
            when (it) {
                is NavigateToSecond -> controller.navigate(it.route)
            }
        }
    }
}

@Composable
private fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
            TextTitleLarge("D_Two::Home")
        }
        Spacer32()

        when (state.loading) {
            null -> Unit
            true -> CircularProgressIndicator()
            false -> Column(Modifier.padding(horizontal = dp16)) {
                TextTitleLarge("Loaded Value - ${state.loadedValue}")
                Spacer32()
                Item(
                    title = "Second Screen",
                    loading = state.submissionLoading,
                    onClick = viewModel::onSecondClicked
                )
            }
        }
    }
}