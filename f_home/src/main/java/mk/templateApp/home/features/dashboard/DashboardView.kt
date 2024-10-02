package mk.templateApp.home.features.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import mk.templateApp.commonPresenter.base.BaseComposeFragment
import mk.templateApp.commonPresenter.base.observeAsState
import javax.inject.Inject

@DashboardScope
class DashboardView : BaseComposeFragment("Dashboard") {

    @Inject
    internal lateinit var router: DashboardRouter

    private val viewModel: DashboardViewModel by viewModels { factory }

    override fun loadInitialData() {
        super.loadInitialData()
        viewModel.loadInitialData()
    }

    override fun observeLiveData() {
        viewModel.navEventsState.observe(this) {
            when (it) {
                DashboardNavEvent.NavigateBack -> router.navigateBack()
                DashboardNavEvent.NavigateToFeatureA -> router.navigateToFeatureA()
            }
        }
    }

    @Composable
    override fun RootView() {
        val vmState: DashboardViewState by viewModel.observeAsState()
        DashboardComposeView(
            vmState,
            backIconOnClick = { viewModel.onBackClicked() },
            featureAClick = { viewModel.featureAClicked() },
        )
    }
}
