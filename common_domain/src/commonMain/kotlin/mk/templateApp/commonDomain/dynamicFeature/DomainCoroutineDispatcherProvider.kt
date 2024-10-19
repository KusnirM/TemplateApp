package mk.templateApp.commonDomain.dynamicFeature

import kotlinx.coroutines.CoroutineDispatcher

interface DomainCoroutineDispatcherProvider {

    fun getIO(): CoroutineDispatcher
}
