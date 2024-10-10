package mk.templateApp.one.domain.interactor

import mk.templateApp.commonDomain.dynamicFeature.DomainCoroutineDispatcherProvider
import mk.templateApp.commonDomain.usecases.base.UseCase
import mk.templateApp.one.domain.repositories.HomeRepository

class GetDataForHomeScreenUseCase(
    private val homeRepository: HomeRepository,
    dispatcherProvider: DomainCoroutineDispatcherProvider
) : UseCase<Unit, DataForHomeScreen>(dispatcherProvider.getIO()) {


    override suspend fun run(params: Unit): DataForHomeScreen {
        return DataForHomeScreen(
            biometricsEnabled = homeRepository.getSettings().biometricsEnabled
        )
    }
}

data class DataForHomeScreen(
    val biometricsEnabled: Boolean
)