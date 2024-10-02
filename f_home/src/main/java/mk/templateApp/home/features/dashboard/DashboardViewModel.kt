package mk.templateApp.home.features.dashboard

import androidx.lifecycle.viewModelScope
import mk.templateApp.commonPresenter.base.BaseViewModel
import mk.templateApp.commonPresenter.base.NavEvent
import mk.templateApp.commonPresenter.base.ViewState
import mk.templateApp.commonPresenter.util.InteractorRunner
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
//    private val getDataForDashboardScreenInteractor: GetDataForDashboardScreenInteractor,
    private val interactorRunner: InteractorRunner,
) : BaseViewModel<DashboardViewState, DashboardNavEvent>() {

    override fun defaultState(): DashboardViewState = DashboardViewState()

    internal fun onBackClicked() {
        navEvent(DashboardNavEvent.NavigateBack)
    }

    internal fun featureAClicked() {
        navEvent(DashboardNavEvent.NavigateToFeatureA)
    }

    internal fun loadInitialData() {
        interactorRunner(
            job = viewModelScope,
            action = {
//                getDataForDashboardScreenInteractor()
            },
            onSuccess = { result ->
                newState { it.copy(loading = false, message = "") }
            },
            onError = { e ->
                newState { it.copy(errorCode = e.errorCode) }
            }
        )
    }
}

data class DashboardViewState(
    val loading: Boolean = true,
    val errorCode: String? = null,
    val message: String? = null,
): ViewState

sealed interface DashboardNavEvent : NavEvent {

    data object NavigateBack : DashboardNavEvent
    data object NavigateToFeatureA : DashboardNavEvent
}
