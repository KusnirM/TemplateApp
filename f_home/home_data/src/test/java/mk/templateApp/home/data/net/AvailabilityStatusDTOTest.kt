package com.vhi.chatSupport.data.datasource.net

import mk.templateApp.home.data.dto.AvailabilityStatusDTO
import mk.templateApp.home.domain.models.AvailabilityStatus
import ie.vhi.commonTest.base.test
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AvailabilityStatusDTOTest : BaseDTOTest<AvailabilityStatusDTO>(
    AvailabilityStatusDTO::class.java
) {
    override var serverJson: String = """
        {"isChatAvailable":"true","availabilityStatus":"AVAILABLE_IMMEDIATELY","availableSlotsCount":1}
        """

    override fun assertClassUnderTestValues(classUnderTest: AvailabilityStatusDTO) {
        assertEquals("AVAILABLE_IMMEDIATELY", classUnderTest.status, "status should match")
    }

    @Test
    fun `testTransform WHEN json have AVAILABLE_IMMEDIATELY THEN check it return AVAILABLE`() = runTest {
        val classUnderTest: AvailabilityStatusDTO = adapter.fromJson(serverJson)!!
        test(
            whenAction = { classUnderTest.transform() },
            then = { model ->
                assertEquals(AvailabilityStatus.AVAILABLE, model)
            }
        )
    }

    @Test
    fun `testTransform WHEN json have CLOSED THEN check it return AVAILABLE`() = runTest {
        val serverJson = """
        {"isChatAvailable":"true","availabilityStatus":"CLOSED","availableSlotsCount":1}
        """
        val classUnderTest: AvailabilityStatusDTO = adapter.fromJson(serverJson)!!
        test(
            whenAction = { classUnderTest.transform() },
            then = { model ->
                assertEquals(AvailabilityStatus.OFFLINE, model)
            }
        )
    }

    @Test
    fun `testTransform WHEN json have THRESHOLD_REACHED THEN check it return BUSY`() = runTest {
        val serverJson = """
        {"isChatAvailable":"true","availabilityStatus":"THRESHOLD_REACHED","availableSlotsCount":1}
        """
        val classUnderTest: AvailabilityStatusDTO = adapter.fromJson(serverJson)!!
        test(
            whenAction = { classUnderTest.transform() },
            then = { model ->
                assertEquals(AvailabilityStatus.BUSY, model)
            }
        )
    }

    @Test
    fun `testTransform WHEN json have NO_AGENTS_AVAILABLE THEN check it return NO_AGENTS`() = runTest {
        val serverJson = """
        {"isChatAvailable":"true","availabilityStatus":"NO_AGENTS_AVAILABLE","availableSlotsCount":1}
        """
        val classUnderTest: AvailabilityStatusDTO = adapter.fromJson(serverJson)!!
        test(
            whenAction = { classUnderTest.transform() },
            then = { model ->
                assertEquals(AvailabilityStatus.NO_AGENTS, model)
            }
        )
    }

    @Test
    fun `testTransform WHEN json have empty status THEN check it return ERROR`() = runTest {
        val serverJson = """
        {"isChatAvailable":"true","availabilityStatus":"","availableSlotsCount":1}
        """
        val classUnderTest: AvailabilityStatusDTO = adapter.fromJson(serverJson)!!
        test(
            whenAction = { classUnderTest.transform() },
            then = { model ->
                assertEquals(AvailabilityStatus.ERROR, model)
            }
        )
    }
}
