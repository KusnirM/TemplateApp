package mk.templateApp.one.domain.interactor.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.commonTest.CoroutineTestExtension
import org.junit.jupiter.api.extension.RegisterExtension

@ExperimentalCoroutinesApi
abstract class BaseCoroutineTest<ClassUnderTest> : BaseTest<ClassUnderTest>() {

    companion object {
        @JvmField
        @RegisterExtension
        val coroutineTestExtension = CoroutineTestExtension()
    }

    override val dispatcherProvider: DomainCoroutineDispatcherProvider =
        object : DomainCoroutineDispatcherProvider {
            override fun getIO(): CoroutineDispatcher = StandardTestDispatcher()
        }

    fun runTest(block: suspend TestScope.() -> Unit): Unit =
        kotlinx.coroutines.test.runTest(context = coroutineTestExtension.dispatcher, testBody = block)
}
