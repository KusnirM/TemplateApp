package mk.templateApp.commonDomain.base

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {

    fun getDefault(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getUnconfined(): CoroutineDispatcher
    fun getIO(): CoroutineDispatcher
}