package mk.templateApp.home.data.net

import mk.templateApp.home.data.dto.AvailabilityStatusDTO
import mk.templateApp.commonData.ext.returnOrThrow
import mk.templateApp.commonDomain.connectivity.NetworkObserver
import javax.inject.Inject

class ChatSupportClientImpl(
    private val chatSupportApi: ChatSupportApi,
    private val networkObserver: NetworkObserver,
) : ChatSupportClient {
    override suspend fun checkAvailability(): AvailabilityStatusDTO =
        returnOrThrow(networkObserver = networkObserver) {
            chatSupportApi.getChatAvailability()
        }
}
