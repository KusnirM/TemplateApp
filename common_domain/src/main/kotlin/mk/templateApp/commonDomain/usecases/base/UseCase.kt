package mk.templateApp.commonDomain.usecases.base


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class UseCase<in Params, Result>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    abstract suspend fun run(params: Params): Result

    suspend operator fun invoke(params: Params, isAsync: Boolean = true): Result = coroutineScope {
        return@coroutineScope if (isAsync) {
            val job = async(coroutineDispatcher) { run(params) }
            job.await()
        } else {
            run(params)
        }
    }
}

suspend operator fun <T> UseCase<Unit, T>.invoke(isAsync: Boolean = true): T =
    invoke(Unit, isAsync)

