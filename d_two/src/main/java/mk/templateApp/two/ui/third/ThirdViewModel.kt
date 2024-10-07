package mk.templateApp.two.ui.third

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mk.templateApp.presenter.base.NavEvent
import mk.templateApp.two.ui.BaseComposeViewModel
import mk.templateApp.two.ui.dynamic.Route
import javax.inject.Inject

@ThirdScope
internal class ThirdViewModel @Inject constructor() : BaseComposeViewModel<ThirdState, NavEvent>(ThirdState()) {


    fun loadInitialData(args: Route.Third) {
        newState { it.copy(arg = args.arg) }
        if (requireState().loading == false) {
            return
        }
        viewModelScope.launch {
            newState { it.copy(loading = true) }
            delay(500)
            newState {
                it.copy(
                    loading = false,
                    loadedValue = "loaded value3"
                )
            }
        }
    }
}


data class ThirdState(val loading: Boolean? = null, val arg: String = "", val loadedValue: String = "")
