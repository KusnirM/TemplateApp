package mk.templateApp.commonDomain.base

import kotlinx.coroutines.CoroutineDispatcher

interface DomainCoroutineDispatcherProvider {

    fun getIO(): CoroutineDispatcher
}
