package mk.templateApp.commonPresenter.base

import kotlinx.coroutines.CoroutineDispatcher
import mk.templateApp.commonDomain.base.CoroutineDispatcherProvider
import mk.templateApp.commonDomain.base.DomainCoroutineDispatcherProvider
import javax.inject.Inject

class DomainCoroutineDispatcherProviderImpl @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : DomainCoroutineDispatcherProvider {

    override fun getIO(): CoroutineDispatcher = coroutineDispatcherProvider.getIO()
}
