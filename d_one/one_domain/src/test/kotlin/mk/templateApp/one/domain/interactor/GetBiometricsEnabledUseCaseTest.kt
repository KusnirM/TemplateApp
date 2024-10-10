package mk.templateApp.one.domain.interactor

import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mk.templateApp.commonDomain.usecases.base.invoke
import mk.templateApp.commonTest.test
import mk.templateApp.one.domain.interactor.base.BaseCoroutineTest
import mk.templateApp.one.domain.models.SettingsModel
import mk.templateApp.one.domain.repositories.HomeRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GetBiometricsEnabledUseCaseTest : BaseCoroutineTest<GetDataForHomeScreenUseCase>() {
    override lateinit var classUnderTest: GetDataForHomeScreenUseCase

    @MockK
    private lateinit var homeRepository: HomeRepository


    @BeforeEach
    override fun setUp() {
        super.setUp()
        classUnderTest = GetDataForHomeScreenUseCase(
            homeRepository = homeRepository,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Test
    fun `test success`(): Unit = runTest {
        test(
            given = {
                var settingsModel = mockk<SettingsModel> {
                    every { biometricsEnabled } returns true
                }
                coEvery { homeRepository.getSettings() } returns settingsModel
            },
            whenAction = {
                classUnderTest()
            },
            then = {
                Assertions.assertEquals(DataForHomeScreen(true), it)
            }
        )
    }
}
