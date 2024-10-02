package mk.templateApp.home.data.net

import mk.templateApp.home.data.dto.AvailabilityStatusDTO

interface ChatSupportClient {
    suspend fun checkAvailability(): AvailabilityStatusDTO
}
