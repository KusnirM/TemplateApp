package mk.templateApp.two.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val favourite: Boolean = false,
)
