package mk.templateApp.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import mk.templateApp.commonPresenter.base.ViewModelFactory
import mk.templateApp.commonPresenter.base.ViewModelKey
import mk.templateApp.home.features.dashboard.DashboardViewModel
import mk.templateApp.home.features.screenB.ScreenBViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun dashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScreenBViewModel::class)
    internal abstract fun screenBViewModel(viewModel: ScreenBViewModel): ViewModel
}
