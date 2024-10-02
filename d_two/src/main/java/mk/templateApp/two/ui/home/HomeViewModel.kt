package mk.templateApp.two.ui.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mk.templateApp.presenter.base.NavEvent
import mk.templateApp.two.ui.BaseComposeViewModel
import mk.templateApp.two.ui.dynamic.Route
import javax.inject.Inject

internal class HomeViewModel @Inject constructor() : BaseComposeViewModel<HomeState, HomeNavEvent>(HomeState()) {

    fun loadInitialData() {
        if (requireState().loading == false) {
            return
        }
        viewModelScope.launch {
            newState { it.copy(loading = true) }
            delay(1000)
            newState {
                it.copy(
                    loading = false,
                    loadedValue = "1"
                )
            }
        }
    }

    fun onSecondClicked() {
        route(HomeNavEvent.NavigateToSecond(Route.Second("2")))
    }

}

data class HomeState(val loading: Boolean? = null, val loadedValue: String = "")

sealed interface HomeNavEvent : NavEvent {
    data class NavigateToSecond(val route: Route.Second) : HomeNavEvent
}


