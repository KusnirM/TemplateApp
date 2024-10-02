package mk.templateApp.domain.exceptions

class FailedToInitializeFeatureException(
    override val message: String?,
    override val cause: Throwable?,
) : RuntimeException()
