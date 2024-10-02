package mk.templateApp.commonDomain.exceptions

class FailedRequest(val responseCode: Int, cause: Throwable? = null) :
    BaseException(message = "Failed network call", cause = cause, errorCode = -1)
