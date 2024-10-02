package mk.templateApp.commonDomain.interactors

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mk.templateApp.commonDomain.exceptions.BaseException
import mk.templateApp.commonDomain.exceptions.UnExpectedException

abstract class Interactor<in Params, Result>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    abstract suspend fun run(params: Params): Result

    suspend operator fun invoke(params: Params, isAsync: Boolean = true): Result = coroutineScope {
        return@coroutineScope if (isAsync) {
            val job = async(coroutineDispatcher) { resultOrBaseException { run(params) } }
            job.await()
        } else {
            resultOrBaseException { run(params) }
        }
    }

    private suspend fun <Result> resultOrBaseException(action: suspend () -> Result): Result {
        return try {
            action()
        } catch (e: Exception) {
            throw when (e) {
                is BaseException -> e
                else -> UnExpectedException(e)
            }
        }
    }
}

suspend operator fun <T> Interactor<None, T>.invoke(isAsync: Boolean = true): T =
    invoke(None, isAsync)

object None
