package mk.templateApp.test.common.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * This test rule replaces [Dispatchers.Main] by [TestCoroutineDispatcher], in order to make tests to skip delays and
 * allows the execution of coroutines to be controlled when executing tests.
 *
 * When using this rule, make sure to use [TestCoroutineDispatcher.runBlockingTest] instead of
 * [kotlinx.coroutines.test.runBlockingTest].
 */
@ExperimentalCoroutinesApi
class CoroutineTestExtension(
    val dispatcher: TestDispatcher = StandardTestDispatcher(TestCoroutineScheduler()),
) : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
