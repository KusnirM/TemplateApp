package mk.templateApp.commonPresenter.util

import timber.log.Timber
import javax.inject.Inject

class Logger @Inject constructor() {

    fun logError(throwable: Throwable) {
        Timber.e(throwable)
    }
}
