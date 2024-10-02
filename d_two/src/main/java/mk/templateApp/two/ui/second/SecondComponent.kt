package mk.templateApp.two.ui.second

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import mk.templateApp.presenter.base.ViewModelKey
import javax.inject.Scope

@SecondScope
@Component(modules = [SecondModule::class])
internal interface SecondComponent {


    @Component.Builder
    interface Builder {

        fun build(): SecondComponent
    }

    val vm: SecondViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class SecondScope

@Module
internal interface SecondModule {

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    @SecondScope
    fun secondViewModel(viewModel: SecondViewModel): ViewModel
}
