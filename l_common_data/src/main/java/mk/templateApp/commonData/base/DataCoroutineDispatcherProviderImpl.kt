package mk.templateApp.commonData.base

import kotlinx.coroutines.CoroutineDispatcher
import mk.templateApp.commonDomain.base.CoroutineDispatcherProvider
import mk.templateApp.commonDomain.base.DataCoroutineDispatcherProvider
import javax.inject.Inject

class DataCoroutineDispatcherProviderImpl @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : DataCoroutineDispatcherProvider {

    override fun getIO(): CoroutineDispatcher = coroutineDispatcherProvider.getIO()
}
