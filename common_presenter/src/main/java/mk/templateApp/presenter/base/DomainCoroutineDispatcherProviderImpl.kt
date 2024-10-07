package mk.templateApp.presenter.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import javax.inject.Inject

class DomainCoroutineDispatcherProviderImpl @Inject constructor(
) : DomainCoroutineDispatcherProvider {

    override fun getIO(): CoroutineDispatcher = Dispatchers.IO
}
