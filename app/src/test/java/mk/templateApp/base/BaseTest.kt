package mk.templateApp.base

import io.mockk.MockKAnnotations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mk.templateApp.commonDomain.base.DomainCoroutineDispatcherProvider

abstract class BaseTest<ClassUnderTest> {
    abstract var classUnderTest: ClassUnderTest

    protected open val dispatcherProvider: DomainCoroutineDispatcherProvider =
        object : DomainCoroutineDispatcherProvider {
            override fun getIO(): CoroutineDispatcher = Dispatchers.Default
        }

    open fun setUp() { // init Mocks
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}