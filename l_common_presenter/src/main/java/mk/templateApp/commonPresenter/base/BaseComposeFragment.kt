package mk.templateApp.commonPresenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

abstract class BaseComposeFragment(private val screenName: String? = null) : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var factory: ViewModelFactory

    // HasAndroidInjector expects AndroidInjector<Any> but gets AndroidInjector<Fragment>
    @Suppress("UNCHECKED_CAST")
    override fun androidInjector(): AndroidInjector<Any> = suppFragmentInjector as AndroidInjector<Any>

    private val suppFragmentInjector = AndroidInjector<Fragment> { instance ->
        if (fragmentInjector.maybeInject(instance)) {
            return@AndroidInjector
        }
        throw IllegalStateException(
            "Injector not found for $instance\n" + """
            Have you added an injector record to your featureModule/.../Bindings.kt ? 
            Probably needs something like:              
            @YourFeatureScreenScope                     
            @ContributesAndroidInjector(modules = [YourFeatureScreenModule::class])
            abstract fun yourScreen(): YourScreenView
        """.trimIndent()
        )
    }

    @Composable
    abstract fun RootView()

    private fun trackScreenView() {
        screenName?.let { Timber.d("Screen Name: $it") }
    }

    open fun observeLiveData() {
        // NO-OP
    }

    open fun loadInitialData() {
        // NO-OP
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (this is FeatureLoadingView) {
            loadFeature(requireContext())
        }
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        trackScreenView()
        observeLiveData()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        loadInitialData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            RootView()
        }
    }

    protected fun onBackPressed(block: () -> Unit = {}) {
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                block()
            }
        })
    }
}
