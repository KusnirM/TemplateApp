package mk.templateApp.two.ui.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mk.templateApp.presenter.base.NavEvent
import mk.templateApp.presenter.base.UseCaseRunner
import mk.templateApp.two.ui.BaseComposeViewModel
import mk.templateApp.two.ui.dynamic.Route
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val useCaseRunner: UseCaseRunner,
) : BaseComposeViewModel<HomeState, HomeNavEvent>(HomeState()) {

    fun loadInitialData() {
        if (requireState().loading == false) {
            return
        }
        viewModelScope.launch {
            newState { it.copy(loading = true) }
            delay(500)
            newState {
                it.copy(
                    loading = false,
                    loadedValue = "1",
                )
            }
        }
    }

    fun onSecondClicked() {
        navEvent(HomeNavEvent.NavigateToSecond(Route.Caching("1->2")))
    }

    fun onThirdClicked() {
        navEvent(HomeNavEvent.NavigateToThird(Route.Third("1->3")))
    }

}

data class HomeState(
    val loading: Boolean? = null,
    val submissionLoading: Boolean = false,
    val loadedValue: String = ""
)

sealed interface HomeNavEvent : NavEvent {
    data class NavigateToSecond(val route: Route.Caching) : HomeNavEvent
    data class NavigateToThird(val route: Route.Third) : HomeNavEvent
}


