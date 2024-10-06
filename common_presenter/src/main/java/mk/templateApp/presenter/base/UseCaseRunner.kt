package mk.templateApp.presenter.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UseCaseRunner @Inject constructor() {
    operator fun <Result> invoke(
        job: CoroutineScope,
        action: suspend () -> Result,
        onError: (Exception) -> Unit = {},
        onSuccess: suspend (Result) -> Unit = {},
        preAction: suspend () -> Unit = {},
    ) = job.launch {
        try {
            preAction()
            onSuccess(action())
        } catch (e: Exception) {
            println(e)
            onError(e)
        }
    }
}
