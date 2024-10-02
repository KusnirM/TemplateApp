package mk.templateApp.home.features.dashboard

import androidx.fragment.app.Fragment
import mk.templateApp.commonPresenter.base.BaseRouter
import javax.inject.Inject

@DashboardScope
class DashboardRouter @Inject constructor(fragment: Fragment) : BaseRouter(fragment) {

    fun navigateToFeatureA() {
        navigate(DashboardViewDirections.actionHomeToScreenB("from feature A"))
    }
}
