package mk.templateApp.two.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import mk.templateApp.AndroidApp
import mk.templateApp.presenter.base.daggerViewModel
import mk.templateApp.two.ui.movieList.MovieListViewModel
import mk.templateApp.two.ui.home.DaggerHomeComponent
import mk.templateApp.two.ui.home.HomeViewModel
import mk.templateApp.two.ui.third.DaggerThirdComponent
import mk.templateApp.two.ui.third.ThirdViewModel

internal object ViewModelProvider {

    @Composable
    private fun featureComponent(): FeatureComponent = DaggerFeatureComponent.builder()
        .applicationComponent((LocalContext.current.applicationContext as AndroidApp).applicationComponent).build()

    @Composable
    internal fun homeViewModel(): HomeViewModel = daggerViewModel(DaggerHomeComponent.create().viewModel)

    @Composable
    internal fun movieListViewModel(): MovieListViewModel = daggerViewModel(
        featureComponent().movieListComponentBuilder().build().viewModel
    )

    @Composable
    internal fun thirdViewModel(): ThirdViewModel = daggerViewModel(DaggerThirdComponent.create().viewModel)
}
