package mk.templateApp.test.common.base

import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.justRun
import io.mockk.verify

fun <T> verifyTest(verify: () -> Unit, whenAction: () -> T) {
    justRun { verify() }
    whenAction()
    verify { verify() }
}

suspend fun <T> coVerifyTest(verify: suspend() -> Unit, whenAction: suspend() -> T) {
    coJustRun { verify() }
    whenAction()
    coVerify { verify() }
}
