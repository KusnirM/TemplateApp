package mk.templateApp.home.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import mk.templateApp.home.domain.models.AvailabilityStatus
import mk.templateApp.commonData.dto.TransformToDomainModel

@Keep
data class AvailabilityStatusDTO(
    @SerializedName(value = "availabilityStatus")
    val status: String,
) : TransformToDomainModel<AvailabilityStatus> {
    override fun transform(): AvailabilityStatus = AvailabilityStatus(status)
}
