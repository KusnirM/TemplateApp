package com.vhi.chatSupport.data.repository

import mk.templateApp.home.data.net.AClient
import mk.templateApp.home.data.net.ChatSupportClient
import com.vhi.chatSupport.data.dto.QuestionnaireRequestDTO
import mk.templateApp.test.common.base.BaseCoroutineTest
import ie.vhi.commonDomain.repositories.AuthRepository
import ie.vhi.commonTest.base.test
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import mk.templateApp.home.data.repository.PremiumRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class PremiumRepositoryImplTest : BaseCoroutineTest<PremiumRepositoryImpl>() {

    override lateinit var classUnderTest: PremiumRepositoryImpl

    @MockK
    private lateinit var aClient: AClient

    @MockK
    private lateinit var feedbackClient: ChatSupportClient

    @MockK
    private lateinit var authRepository: AuthRepository

    private val firstName = "firstName"

    private val launchCode = "launchCode"

    @BeforeEach
    override fun setUp() {
        super.setUp()
        classUnderTest = PremiumRepositoryImpl(
            aClient,
            feedbackClient,
            authRepository,
        )
    }

    @Test
    fun `test getChatSession THEN check flow emits new messages`() = runTest {
        val messages = emptyFlow<Message>()
        test(
            given = {
                every { aClient.getChatSession() } returns messages
            },
            whenAction = { classUnderTest.getChatSession() },
            then = {
                assertEquals(messages, it)
            }
        )
    }

    @Test
    fun `test sendMessage THEN check flow emits new messages`() = runTest {
        val message: Message.User = mockk()
        test(
            given = {
                every { aClient.sendMessage(message) } returns true
            },
            whenAction = { classUnderTest.sendMessage(message) },
            then = {
                assertTrue(it)
            }
        )
    }

    @Test
    fun `testDisconnect THEN verify it called disconnect`() = runTest {
        test(
            given = {
                coJustRun { aClient.disconnect() }
            },
            whenAction = { classUnderTest.disconnect() },
            then = {
                coVerify { aClient.disconnect() }
            }
        )
    }

    @Test
    fun `testInitFlow THEN verify it called connect`() = runTest {
        test(
            given = {
                coJustRun { aClient.initFlow(firstName, launchCode) }
            },
            whenAction = { classUnderTest.initFlow(firstName, launchCode) },
            then = {
                coVerify { aClient.initFlow(firstName, launchCode) }
            }
        )
    }

    @Test
    fun `testAgentName THEN verify agent name`() = runTest {
        test(
            given = {
                every { aClient.getAgentName() } returns "agent"
            },
            whenAction = { classUnderTest.getAgentName() },
            then = {
                assertEquals("agent", it)
            }
        )
    }

    @Test
    fun `test submitFeedback THEN verify it called`() = runTest {
        val request: List<QuestionnaireRequest> = listOf(
            QuestionnaireRequest(
                0, 1, ""
            )
        )
        val vhiSurveyId = "vhiSurveyId"
        test(
            given = {
                every { authRepository.vhiSurveyId } returns vhiSurveyId
                coJustRun {
                    feedbackClient.submitFeedback(
                        request = listOf(QuestionnaireRequestDTO(0, 1, "")),
                        vhiSurveyId = vhiSurveyId
                    )
                }
            },
            whenAction = { classUnderTest.submitFeedback(request) },
            then = {
                coVerify {
                    feedbackClient.submitFeedback(
                        request = listOf(QuestionnaireRequestDTO(0, 1, "")),
                        vhiSurveyId = vhiSurveyId
                    )
                }
            }
        )
    }

    @Test
    fun `test getChatHistoryDetails THEN check it return chatText value`() = runTest {
        val policyHolder = ""
        val chatId = 1
        val chatTexts: List<String> = mockk()
        test(
            given = {
                coEvery {
                    feedbackClient.getChatHistoryDetails(policyHolder, chatId)
                } returns mockk {
                    every { chatText } returns chatTexts
                }
            },
            whenAction = { classUnderTest.getChatHistoryDetails(policyHolder, chatId) },
            then = {
                assertEquals(chatTexts, it)
            }
        )
    }
}
