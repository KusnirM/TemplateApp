package mk.templateApp.two.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mk.templateApp.presenter.base.NavEvent

abstract class BaseComposeViewModel<T, NE : NavEvent>(initValue: T) : ViewModel() {

    private val _state = MutableStateFlow(initValue)
    val state: StateFlow<T> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = initValue
        )

    private val _navEvent = Channel<NE>()
    val navEvent: Flow<NE> = _navEvent.receiveAsFlow()

    protected fun navEvent(event: NE) {
        viewModelScope.launch {
            _navEvent.send(event)
        }
    }

    protected fun newState(stateCopy: (T) -> T) {
        val oldState = _state.value!!
        _state.value = stateCopy(oldState)
    }

    protected fun requireState(): T = _state.value!!
}
