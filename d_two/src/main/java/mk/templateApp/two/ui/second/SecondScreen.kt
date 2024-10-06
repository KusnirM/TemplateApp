package mk.templateApp.two.ui.second

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
import mk.templateApp.two.ui.dynamic.Route.Second
import mk.templateApp.two.ui.second.SecondNavEvent.NavigateToThird


internal fun NavGraphBuilder.second(navController: NavController) {
    composable<Second> { backStackEntry ->
        val route: Second = backStackEntry.toRoute()
        val viewModel: SecondViewModel = ViewModelProvider.secondViewModel()
        viewModel.loadInitialData(route)
        SecondScreen(viewModel = viewModel)
        ObserveEvent(viewModel.navEvent) {
            when (it) {
                is NavigateToThird -> navController.navigate(it.route)
            }
        }
    }
}

@Composable
private fun SecondScreen(viewModel: SecondViewModel) {
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
            TextTitleLarge("D_two Second-${state.id}")
        }
        Spacer32()

        when (state.loading) {
            true -> CircularProgressIndicator()
            false -> Column(Modifier.padding(horizontal = dp16)) {
                Item(
                    title = "Third Screen",
                    onClick = viewModel::onThirdClicked
                )
            }

            null -> Unit
        }
    }
}