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
                    loading = false, loadedValue = "1"
                )
            }
        }
    }

    fun onSecondClicked() {
        useCaseRunner(job = viewModelScope, preAction = {
            newState { it.copy(submissionLoading = true) }
        }, action = {
            delay(2000)
        }, onSuccess = {
            newState { it.copy(submissionLoading = false) }
            navEvent(HomeNavEvent.NavigateToSecond(Route.Second("2")))
        })
    }

}

data class HomeState(
    val loading: Boolean? = null,
    val submissionLoading: Boolean = false,
    val loadedValue: String = ""
)

sealed interface HomeNavEvent : NavEvent {
    data class NavigateToSecond(val route: Route.Second) : HomeNavEvent
}


