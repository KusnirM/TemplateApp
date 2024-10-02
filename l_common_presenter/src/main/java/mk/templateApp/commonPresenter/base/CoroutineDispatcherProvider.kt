package mk.templateApp.commonPresenter.base

import kotlinx.coroutines.Dispatchers
import mk.templateApp.commonDomain.base.CoroutineDispatcherProvider
import javax.inject.Inject


class CoroutineDispatcherProviderImpl @Inject constructor() : CoroutineDispatcherProvider {

    override fun getDefault() = Dispatchers.Default
    override fun getMain() = Dispatchers.Main
    override fun getUnconfined() = Dispatchers.Unconfined
    override fun getIO() = Dispatchers.IO
}
