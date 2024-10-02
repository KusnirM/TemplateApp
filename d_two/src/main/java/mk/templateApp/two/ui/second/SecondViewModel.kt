package mk.templateApp.two.ui.second

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mk.templateApp.presenter.base.NavEvent
import mk.templateApp.two.ui.BaseComposeViewModel
import mk.templateApp.two.ui.dynamic.Route
import javax.inject.Inject

internal class SecondViewModel @Inject constructor() :
    BaseComposeViewModel<SecondViewState, SecondNavEvent>(SecondViewState()) {

    fun loadInitialData(args: Route.Second) {
        newState { it.copy(id = args.arg) }
        if (requireState().loading == false) {
            return
        }
        viewModelScope.launch {
            newState { it.copy(loading = true) }
            delay(1000)
            newState {
                it.copy(
                    loading = false,
                    loadedValue = "loaded value"
                )
            }
        }
    }

    fun onThirdClicked() {
        route(SecondNavEvent.NavigateToThird(Route.Third("3")))
    }

}


data class SecondViewState(val loading: Boolean? = null, val id: String = "", val loadedValue: String = "")
sealed interface SecondNavEvent : NavEvent {
    data class NavigateToThird(val route: Route.Third) : SecondNavEvent
}