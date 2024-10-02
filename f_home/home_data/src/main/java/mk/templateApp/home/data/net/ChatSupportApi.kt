package mk.templateApp.home.data.net

import mk.templateApp.home.data.dto.AvailabilityStatusDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatSupportApi {

    @GET("/api/v1/availability")
    suspend fun getChatAvailability(
        @Query("launchCode") code: String = "",
        @Query("queues") queue: String = "",
    ): Response<AvailabilityStatusDTO>

}
