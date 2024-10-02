package mk.templateApp.two.ui.third

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import mk.templateApp.presenter.base.ViewModelKey
import mk.templateApp.two.ui.third.ThirdViewModel
import javax.inject.Scope

@ThirdScope
@Component(modules = [ThirdModule::class])
internal interface ThirdComponent {


    @Component.Builder
    interface Builder {

        fun build(): ThirdComponent
    }

    val vm: ThirdViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ThirdScope

@Module
internal interface ThirdModule {

    @Binds
    @IntoMap
    @ViewModelKey(ThirdViewModel::class)
    @ThirdScope
    fun thirdViewModel(viewModel: ThirdViewModel): ViewModel
}
