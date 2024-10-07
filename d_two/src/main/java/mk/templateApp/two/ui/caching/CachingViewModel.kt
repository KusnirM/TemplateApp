package mk.templateApp.two.ui.caching

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mk.templateApp.presenter.base.NavEvent
import mk.templateApp.presenter.base.UseCaseRunner
import mk.templateApp.two.ui.BaseComposeViewModel
import mk.templateApp.two.ui.dynamic.Route
import javax.inject.Inject

internal class CachingViewModel @Inject constructor(
//    private val getFavouriteMoviesUseCase : GetFavouriteMoviesUseCase,
//    private val useCaseRunner: UseCaseRunner,
) :
    BaseComposeViewModel<CachingState, CachingNavEvent>(CachingState()) {

    fun loadInitialData(args: Route.Caching) {
        newState { it.copy(args = args.arg) }
        if (requireState().loading == false) {
            return
        }
        viewModelScope.launch {
            newState { it.copy(loading = true) }
            delay(500)
            newState {
                it.copy(
                    loading = false,
                    loadedValue = "loaded value"
                )
            }
        }
    }

    fun onThirdClicked() {
        navEvent(CachingNavEvent.NavigateToThird(Route.Third("2-3")))
    }

}


data class CachingState(val loading: Boolean? = null, val args: String = "", val loadedValue: String = "")
sealed interface CachingNavEvent : NavEvent {
    data class NavigateToThird(val route: Route.Third) : CachingNavEvent
}