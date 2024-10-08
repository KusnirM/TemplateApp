package mk.templateApp.presenter.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel<VIEW_STATE : ViewState, NAV_EVENT : NavEvent> : ViewModel() {

    abstract fun defaultState(): VIEW_STATE

    protected val _viewState = MutableLiveData<VIEW_STATE>().apply {
        value = defaultState()
    }

    protected val _navState = SingleLiveData<NAV_EVENT>()

    val viewState: LiveData<VIEW_STATE> = _viewState
    val navEventsState: LiveData<NAV_EVENT> = _navState

    protected fun newState(stateCopy: (VIEW_STATE) -> VIEW_STATE) {
        val oldState = _viewState.value!!
        _viewState.value = stateCopy(oldState)
    }

    protected fun navEvent(navEvent: NAV_EVENT) {
        _navState.value = navEvent
    }

    protected fun requireState(block: (VIEW_STATE) -> Unit): Unit = block(_viewState.value!!)

    protected fun requireState(): VIEW_STATE = _viewState.value!!
}

interface NavEvent
interface ViewState
class DefaultViewState : ViewState

@Composable
fun <T : ViewState, Nav_event : NavEvent> BaseViewModel<T, Nav_event>.observeAsState(): State<T> =
    viewState.observeAsState(defaultState())


class SingleLiveData<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    override fun observe(owner: androidx.lifecycle.LifecycleOwner, observer: Observer<in T>) {
        require(!hasActiveObservers()) {
            "SingleLiveEvent already has registered an observer"
        }

        super.observe(owner) {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
    }

    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }
}
