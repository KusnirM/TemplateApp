package mk.templateApp.two.ui.dynamic

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import javax.inject.Scope

@Module
abstract class DynamicActivityModule {

    @Binds
    abstract fun bindAppCompatActivity(activity: DynamicActivity): AppCompatActivity
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DynamicActivityScope