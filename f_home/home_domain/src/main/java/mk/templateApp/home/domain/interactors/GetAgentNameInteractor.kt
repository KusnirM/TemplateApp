package mk.templateApp.home.domain.interactors

import mk.templateApp.home.domain.repositories.PremiumRepository
import mk.templateApp.commonDomain.base.DomainCoroutineDispatcherProvider
import mk.templateApp.commonDomain.interactors.Interactor
import mk.templateApp.commonDomain.interactors.None

class GetAgentNameInteractor(
    private val premiumRepository: PremiumRepository,
    dispatcherProvider: DomainCoroutineDispatcherProvider,
) : Interactor<None, String>(dispatcherProvider.getIO()) {

    override suspend fun run(params: None): String = premiumRepository.getValue()
}
