package mk.templateApp.test.common.base

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

    open fun setUp() { // if we don't call below, we will get NullPointerException
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}