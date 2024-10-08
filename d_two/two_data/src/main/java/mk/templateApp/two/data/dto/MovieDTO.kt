package mk.templateApp.two.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import mk.templateApp.commonDomain.base.TransformToDomain
import mk.templateApp.two.domain.model.Movie


@Serializable
data class MovieDTO(
    val id: Int,
    @SerialName("title")
    val name: String
) : TransformToDomain<Movie> {
    override fun transform(): Movie {
        return Movie(id = id, name = name)
    }
}
