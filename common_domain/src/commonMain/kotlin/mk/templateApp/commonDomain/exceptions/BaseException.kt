package mk.templateApp.commonDomain.exceptions

abstract class BaseException(
    message: String,
    val errorCode: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {

    constructor(message: String, errorCode: Int, cause: Throwable? = null) : this(message, "$errorCode", cause)
}
