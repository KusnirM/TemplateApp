package mk.templateApp.one.ui.home

import mk.templateApp.presenter.base.BaseViewModel
import mk.templateApp.presenter.base.DefaultViewState
import mk.templateApp.presenter.base.NavEvent
import javax.inject.Inject

internal class HomeViewModel @Inject constructor() : BaseViewModel<DefaultViewState, HomeNavEvent>() {

    override fun defaultState(): DefaultViewState = DefaultViewState()

    fun loadInitialData() {

    }

    fun onTwoClicked() {
        navEvent(HomeNavEvent.NavigateToFeature2)
    }
}

sealed interface HomeNavEvent : NavEvent {
    data object NavigateToFeature2 : HomeNavEvent
}
