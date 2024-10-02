package mk.templateApp.test.common.base

import mk.templateApp.test.common.extensions.CoroutineTestExtension
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import mk.templateApp.commonDomain.base.DomainCoroutineDispatcherProvider
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Base class for test classes that will test classes that use coroutines.
 * It does the ceremony of registering the [CoroutineTestExtension] and provides a helper [runTest] function.
 */
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