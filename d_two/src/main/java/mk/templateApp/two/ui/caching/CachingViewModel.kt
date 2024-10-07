package mk.templateApp.two.ui.caching

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mk.templateApp.commonDomain.usecases.base.invoke
import mk.templateApp.presenter.base.NavEvent
import mk.templateApp.presenter.base.UseCaseRunner
import mk.templateApp.two.domain.interactor.GetMoviesUseCase
import mk.templateApp.two.domain.model.Movie
import mk.templateApp.two.ui.BaseComposeViewModel
import mk.templateApp.two.ui.dynamic.Route
import javax.inject.Inject

@CachingScope
internal class CachingViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val useCaseRunner: UseCaseRunner,
) :
    BaseComposeViewModel<CachingState, CachingNavEvent>(CachingState()) {

    fun loadInitialData(args: Route.Caching) {
        newState { it.copy(args = args.arg) }
        if (requireState().loading == false) {
            return
        }

        useCaseRunner<List<Movie>>(
            job = viewModelScope,
            preAction = {
                newState { it.copy(loading = true) }
            },
            action = getMoviesUseCase::invoke,
            onSuccess = { result ->
                newState {
                    it.copy(
                        loading = false,
                        movies = result
                    )
                }
            }
        )
    }
    fun onThirdClicked() {
        navEvent(CachingNavEvent.NavigateToThird(Route.Third("2-3")))
    }

}


data class CachingState(val loading: Boolean? = null, val args: String = "", val movies: List<Movie> = emptyList())
sealed interface CachingNavEvent : NavEvent {
    data class NavigateToThird(val route: Route.Third) : CachingNavEvent
}