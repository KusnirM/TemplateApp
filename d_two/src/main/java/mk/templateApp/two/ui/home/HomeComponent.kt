package mk.templateApp.two.ui.home

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import mk.templateApp.presenter.base.ViewModelKey
import javax.inject.Scope

@HomeScope
@Component(modules = [HomeModule::class])
internal interface HomeComponent {


    @Component.Builder
    interface Builder {

        fun build(): HomeComponent
    }

    val vm: HomeViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class HomeScope

@Module
internal interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @HomeScope
    fun homeViewModel(viewModel: HomeViewModel): ViewModel
}
