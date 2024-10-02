package mk.templateApp.two.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import mk.templateApp.presenter.base.NavEvent

abstract class BaseComposeViewModel<T, NE : NavEvent>(initValue: T) : ViewModel() {

    private val _state = MutableStateFlow(initValue)
    val state: StateFlow<T> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = initValue
        )

    private val _route = MutableStateFlow<NE?>(null)
    val route: StateFlow<NE?> = _route
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = null
        )

    protected fun route(event: NE) {
        _route.value = event
        _route.value = null
    }

    protected fun newState(stateCopy: (T) -> T) {
        val oldState = _state.value!!
        _state.value = stateCopy(oldState)
    }

    protected fun requireState(): T = _state.value!!
}
