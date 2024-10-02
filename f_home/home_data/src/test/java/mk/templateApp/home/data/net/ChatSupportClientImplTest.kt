package mk.templateApp.home.data.net

import mk.templateApp.test.common.base.BaseCoroutineTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mk.templateApp.home.data.dto.AvailabilityStatusDTO
import mk.templateApp.commonDomain.connectivity.ConnectionType
import mk.templateApp.commonDomain.connectivity.NetworkObserver
import mk.templateApp.commonDomain.exceptions.FailedRequest
import mk.templateApp.test.common.base.test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class ChatSupportClientImplTest : BaseCoroutineTest<ChatSupportClientImpl>() {

    override lateinit var classUnderTest: ChatSupportClientImpl

    @MockK
    private lateinit var chatSupportApi: ChatSupportApi

    @MockK
    private lateinit var networkObserver: NetworkObserver

    @BeforeEach
    override fun setUp() {
        super.setUp()
        classUnderTest = ChatSupportClientImpl(chatSupportApi, networkObserver)
    }

    @Test
    fun `test checkAvailability THEN verify success`() = runTest {
        val launchCode = "launchCode"
        val queue = "queue"
        test(
            given = {
                every { networkObserver.connectType } returns ConnectionType.CONNECTED
                val response = mockk<Response<AvailabilityStatusDTO>>()
                coEvery { response.code() } returns 200
                coEvery { response.body() } returns mockk()
                coEvery { chatSupportApi.getChatAvailability(launchCode, queue) } returns response
            },
            whenAction = { chatSupportApi.getChatAvailability(launchCode, queue) },
            then = { coVerify { chatSupportApi.getChatAvailability(launchCode, queue) } }
        )
    }

    @Test
    fun `test checkAvailability WHEN has error THEN FailedRequest should capture`() = runTest {
        val launchCode = "launchCode"
        val queue = "queue"
        test(
            given = {
                every { networkObserver.connectType } returns ConnectionType.CONNECTED
                val response = mockk<Response<AvailabilityStatusDTO>>()
                coEvery { response.code() } returns 1
                coEvery { response.body() } returns mockk()
                coEvery { chatSupportApi.getChatAvailability(launchCode, queue) } throws FailedRequest(1)
            },
            whenAction = {
                assertThrows<FailedRequest> {
                    chatSupportApi.getChatAvailability(launchCode, queue)
                }
            },
            then = { }
        )
    }
}
