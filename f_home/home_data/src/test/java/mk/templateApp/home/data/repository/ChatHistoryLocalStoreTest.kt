package com.vhi.chatSupport.data.repository

import mk.templateApp.test.common.base.BaseCoroutineTest
import ie.vhi.commonTest.base.test
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mk.templateApp.home.data.repository.ChatHistoryLocalStore
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatHistoryLocalStoreTest : BaseCoroutineTest<ChatHistoryLocalStore>() {

    override lateinit var classUnderTest: ChatHistoryLocalStore

    @BeforeEach
    override fun setUp() {
        super.setUp()
        classUnderTest = ChatHistoryLocalStore()
    }

    @Test
    fun `test getChatHistoryList WHEN dont have cache value THEN check its null`() = runTest {
        test(
            whenAction = { classUnderTest.getChatHistoryList() },
            then = {
                assertEquals(null, it)
            }
        )
    }

    @Test
    fun `test cacheChatHistoryList WHEN cache list THEN check it return the same`() = runTest {
        val history: List<ChatHistoryItem> = mockk()
        test(
            whenAction = {
                classUnderTest.cacheChatHistoryList(history)
                classUnderTest.getChatHistoryList()
            },
            then = {
                assertEquals(history, it)
            }
        )
    }

    @Test
    fun `test clearChatHistoryCache WHEN have cache THEN check its null`() = runTest {
        `test cacheChatHistoryList WHEN cache list THEN check it return the same`()
        test(
            whenAction = {
                classUnderTest.clearChatHistoryCache()
                classUnderTest.getChatHistoryList()
            },
            then = {
                assertEquals(null, it)
            }
        )
    }
}
