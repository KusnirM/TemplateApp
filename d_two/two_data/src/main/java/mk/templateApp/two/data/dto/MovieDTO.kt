package mk.templateApp.two.data.dto

import mk.templateApp.commonDomain.base.TransformToDomain
import mk.templateApp.two.domain.model.Movie


data class MovieDTO(
    val id: Int,
    val name: String
) : TransformToDomain<Movie> {
    override fun transform(): Movie {
        return Movie(id = id, name = name)
    }
}
