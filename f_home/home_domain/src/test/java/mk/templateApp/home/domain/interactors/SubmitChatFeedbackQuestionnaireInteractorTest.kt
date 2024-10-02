//package com.vhi.chatSupport.domain.interactor
//
//import mk.templateApp.a.domain.repository.ChatRepository
//import com.vhi.test.common.base.BaseCoroutineTest
//import ie.vhi.commonTest.base.test
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.impl.annotations.MockK
//import io.mockk.just
//import io.mockk.runs
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//
//@ExperimentalCoroutinesApi
//class SubmitChatFeedbackQuestionnaireInteractorTest : BaseCoroutineTest<SubmitChatFeedbackQuestionnaireInteractor>() {
//
//    override lateinit var classUnderTest: SubmitChatFeedbackQuestionnaireInteractor
//
//    @MockK
//    private lateinit var chatRepository: ChatRepository
//
//    @BeforeEach
//    override fun setUp() {
//        super.setUp()
//        classUnderTest = SubmitChatFeedbackQuestionnaireInteractor(chatRepository, dispatcherProvider)
//    }
//
//    @Test
//    fun testSuccess() = runTest {
//        val params = SubmitChatFeedbackQuestionnaireInteractor.Params(
//            "Y",
//            "4"
//        )
//        val request = listOf(
//            QuestionnaireRequest(
//                questionId = 1,
//                questionOrder = 1,
//                answer = params.fullyAnswered
//            ),
//            QuestionnaireRequest(
//                questionId = 2,
//                questionOrder = 2,
//                answer = params.rating
//            )
//        )
//        test(
//            given = { coEvery { chatRepository.submitFeedback(request) } just runs },
//            whenAction = { classUnderTest(params) },
//            then = { coVerify { chatRepository.submitFeedback(request) } }
//        )
//    }
//}
