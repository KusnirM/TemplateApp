package mk.templateApp.ui.main

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import javax.inject.Scope

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindAppCompatActivity(activity: MainActivity): AppCompatActivity
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainActivityScope
