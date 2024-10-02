package mk.templateApp.commonPresenter.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mk.templateApp.commonDomain.exceptions.BaseException
import javax.inject.Inject

class InteractorRunner @Inject constructor(
    private val logger: Logger,
) {
    operator fun <Result> invoke(
        job: CoroutineScope,
        action: suspend () -> Result,
        onError: (BaseException) -> Unit = {},
        onSuccess: suspend (Result) -> Unit,
        preAction: suspend () -> Unit = {},
    ) = job.launch {
        try {
            preAction()
            onSuccess(action())
        } catch (e: BaseException) {
            logger.logError(e)
            onError(e)
        }
    }
}
