package mk.templateApp.one.ui.home

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import mk.templateApp.one.ui.BaseView
import javax.inject.Inject

@HomeScope
internal class HomeView : BaseView("Home") {

    @Inject
    lateinit var navigator: HomeNavigator

    private val viewModel: HomeViewModel by viewModels { factory }

    override fun loadInitialData() {
        super.loadInitialData()
        viewModel.loadInitialData()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.navEventsState.observe(this) {
            when (it) {
                HomeNavEvent.NavigateToFeature2 -> navigator.navigateToFeature2()
            }
        }
    }

    @Composable
    override fun RootView() {
        HomeComposeView(twoClick = viewModel::onTwoClicked)
    }
}
