package mk.templateApp.ui.main

import android.os.Bundle
import mk.templateApp.R
import mk.templateApp.commonPresenter.base.InjectionActivity

@MainActivityScope
class MainActivity : InjectionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
    }
}
