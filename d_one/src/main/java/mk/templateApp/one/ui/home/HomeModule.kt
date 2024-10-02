package mk.templateApp.one.ui.home

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
internal abstract class HomeModule {

    @Binds
    abstract fun bindFragment(fragment: HomeView): Fragment

    companion object {

        @HomeScope
        @Provides
        fun provideActivity(fragment: Fragment): Activity = fragment.requireActivity()
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class HomeScope
