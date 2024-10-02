package mk.templateApp.commonPresenter.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class InjectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
