package mk.templateApp.one.ui.home

import androidx.fragment.app.Fragment
import mk.templateApp.presenter.base.Navigator
import javax.inject.Inject

internal class HomeNavigator @Inject constructor(fragment: Fragment) : Navigator(fragment) {

    fun navigateToFeature2() {
        navigate(HomeViewDirections.actionHomeToTwo(argument = "from feauture 1"))
    }
}
