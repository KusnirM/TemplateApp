package mk.templateApp.commonDomain.exceptions

class UnExpectedException(cause: Throwable? = null) :
    BaseException(message = "UnExpected Error", errorCode = -1, cause = cause)