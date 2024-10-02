package com.vhi.chatSupport.data.dto

import org.junit.jupiter.api.Assertions.assertEquals

class ChatHistoryDetailsResponseDTOTest : BaseDTOTest<ChatHistoryDetailsResponseDTO>(
    ChatHistoryDetailsResponseDTO::class.java
) {
    override var serverJson: String = """
        {"chatText":["123", "234"]}
        """

    override fun assertClassUnderTestValues(classUnderTest: ChatHistoryDetailsResponseDTO) {
        assertEquals(listOf("123", "234"), classUnderTest.chatText, "status should match")
    }
}
