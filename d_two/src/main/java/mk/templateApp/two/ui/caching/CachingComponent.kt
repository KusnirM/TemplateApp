package mk.templateApp.two.ui.caching

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import mk.templateApp.presenter.base.ViewModelKey
import mk.templateApp.two.di.TwoDomainInteractorModule
import javax.inject.Scope

@CachingScope
@Subcomponent(modules = [CachingModule::class])
internal interface CachingComponent {


    @Subcomponent.Builder
    interface Builder {

        fun build(): CachingComponent
    }

    val vm: CachingViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CachingScope

@Module(includes = [TwoDomainInteractorModule::class])
internal interface CachingModule {

    @Binds
    @IntoMap
    @ViewModelKey(CachingViewModel::class)
    @CachingScope
    fun secondViewModel(viewModel: CachingViewModel): ViewModel
}
