package com.vhi.chatSupport.data.repository

import com.google.gson.Gson
import com.vhi.chatSupport.data.datasource.net.socket.stomp.StompConnection
import com.vhi.chatSupport.data.datasource.net.socket.stomp.StompHandler
import com.vhi.chatSupport.data.datasource.net.socket.stomp.stompConnection
import com.vhi.chatSupport.data.mapper.ChatSocketMessageMapper
import com.vhi.chatSupport.data.mapper.ChatStompReceiveBodyMapper
import mk.templateApp.test.common.base.BaseCoroutineTest
import ie.vhi.commonData.base.DataCoroutineDispatcherProvider
import ie.vhi.commonDomain.repositories.AuthRepository
import ie.vhi.commonTest.base.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import mk.templateApp.home.data.net.AClientImpl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@Disabled
internal class AClientImplTest : BaseCoroutineTest<AClientImpl>() {

    override lateinit var classUnderTest: AClientImpl

    @MockK
    private lateinit var authRepository: AuthRepository

    @MockK
    private lateinit var okHttpClient: OkHttpClient
    private val apiUri: String = ""

    @MockK
    private lateinit var chatStompReceiveBodyMapper: ChatStompReceiveBodyMapper

    @MockK
    private lateinit var gson: Gson

    @MockK
    private lateinit var chatSocketMessageMapper: ChatSocketMessageMapper

    @MockK
    private lateinit var timeProvider: TimeProvider

    private lateinit var appScope: CoroutineScope

    private val dataDispatcherProvider: DataCoroutineDispatcherProvider = object : DataCoroutineDispatcherProvider {
        override fun getIO(): CoroutineDispatcher = UnconfinedTestDispatcher()
    }

    @BeforeEach
    override fun setUp() {
        super.setUp()
        appScope = TestScope()
        classUnderTest = AClientImpl(
            appScope,
            authRepository, okHttpClient, apiUri, chatStompReceiveBodyMapper, gson,
            chatSocketMessageMapper, timeProvider, dataDispatcherProvider
        )
    }

    private val stompClient: StompHandler = mockk()
    private val stompConnection: StompConnection = mockk()

    @Test
    fun `test connection successfully`() = runTest {
        test(
            given = {
                mockkStatic(Request::class)
                mockkStatic(Request.Builder::class)
                coEvery {
                    okHttpClient.stompConnection(any(), authRepository)
                } returns stompClient
                coEvery {
                    stompClient.connect(any())
                } returns stompConnection
            },
            whenAction = { classUnderTest.connect("", "") {} },
            then = {
                coVerify { okHttpClient.stompConnection(any(), authRepository) }
                coVerify { stompClient.connect(any()) }
            }
        )
    }
}
