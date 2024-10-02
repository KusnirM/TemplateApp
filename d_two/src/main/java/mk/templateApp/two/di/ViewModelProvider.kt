package mk.templateApp.two.di

import androidx.compose.runtime.Composable
import mk.templateApp.presenter.base.daggerViewModel
import mk.templateApp.two.ui.home.DaggerHomeComponent
import mk.templateApp.two.ui.home.HomeViewModel
import mk.templateApp.two.ui.second.DaggerSecondComponent
import mk.templateApp.two.ui.second.SecondViewModel
import mk.templateApp.two.ui.third.DaggerThirdComponent
import mk.templateApp.two.ui.third.ThirdViewModel

internal object ViewModelProvider {

    @Composable
    internal fun homeViewModel(): HomeViewModel = daggerViewModel(DaggerHomeComponent.create().vm)

    @Composable
    internal fun secondViewModel(): SecondViewModel = daggerViewModel(DaggerSecondComponent.create().vm)

    @Composable
    internal fun thirdViewModel(): ThirdViewModel = daggerViewModel(DaggerThirdComponent.create().vm)
}