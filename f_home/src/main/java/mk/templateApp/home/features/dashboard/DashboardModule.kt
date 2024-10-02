package mk.templateApp.home.features.dashboard

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
abstract class DashboardModule {

    @Binds
    abstract fun bindFragment(fragment: DashboardView): Fragment

    companion object {

        @DashboardScope
        @Provides
        fun provideAppCompatActivity(fragment: Fragment): Activity = fragment.requireActivity()
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DashboardScope
