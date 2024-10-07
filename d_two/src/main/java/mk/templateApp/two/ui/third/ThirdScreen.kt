package mk.templateApp.two.ui.third

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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import mk.templateApp.presenter.components.spacers.ColumnSpacer.Spacer32
import mk.templateApp.presenter.components.text.TextTitleLarge
import mk.templateApp.presenter.theming.dp16
import mk.templateApp.two.di.ViewModelProvider
import mk.templateApp.two.ui.dynamic.Route.Third

internal fun NavGraphBuilder.third(){
    composable<Third> { backStackEntry ->
        val screen: Third = backStackEntry.toRoute()
        val viewModel: ThirdViewModel = ViewModelProvider.thirdViewModel()
        ThirdScreen(viewModel = viewModel)
        viewModel.loadInitialData(screen)
    }
}
@Composable
private fun ThirdScreen(viewModel: ThirdViewModel) {
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
            TextTitleLarge("Third - ${state.arg}")
        }
        Spacer32()

        when(state.loading) {
            true -> CircularProgressIndicator()
            false -> TextTitleLarge(text = state.loadedValue)
            null -> Unit
        }
    }
}