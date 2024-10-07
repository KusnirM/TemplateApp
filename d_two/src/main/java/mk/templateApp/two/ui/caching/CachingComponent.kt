package mk.templateApp.two.ui.caching

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import mk.templateApp.presenter.base.ViewModelKey
import javax.inject.Scope

@CachingScope
@Component(modules = [CachingModule::class])
internal interface CachingComponent {


    @Component.Builder
    interface Builder {

        fun build(): CachingComponent
    }

    val vm: CachingViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CachingScope

@Module
internal interface CachingModule {

    @Binds
    @IntoMap
    @ViewModelKey(CachingViewModel::class)
    @CachingScope
    fun secondViewModel(viewModel: CachingViewModel): ViewModel
}
