package mk.templateApp.commonData.ext

import mk.templateApp.commonDomain.connectivity.ConnectionType
import mk.templateApp.commonDomain.connectivity.NetworkObserver
import mk.templateApp.commonDomain.exceptions.FailedRequest
import mk.templateApp.commonDomain.exceptions.NoInternetConnectionException
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_CREATED
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_OK

private const val HTTP_SERVER_ERROR_LAST_CODE = 599

private const val UNKNOWN_ERROR_RESPONSE_CODE = -1

/**
 * Use this function to add retry capabilities to your retrofit calls
 */
private suspend fun <ResponseType> retry(
    count: Int = 1,
    networkObserver: NetworkObserver,
    requestCall: suspend () -> Response<ResponseType>,
): Response<ResponseType> {
    checkConnection(networkObserver)

    try {
        val response = requestCall()
        return when (response.code()) {
            in HTTP_INTERNAL_ERROR..HTTP_SERVER_ERROR_LAST_CODE -> {
                if (count > 0) {
                    retry(count - 1, networkObserver, requestCall)
                } else {
                    response
                }
            }

            else -> response
        }
    } catch (e: Exception) {
        if (count > 0) {
            return retry(count - 1, networkObserver, requestCall)
        }
        throw FailedRequest(UNKNOWN_ERROR_RESPONSE_CODE, e)
    }
}

/**
 * This function will extract the response value or throw an exception
 */
suspend fun <ResponseType> returnOrThrow(
    retryCount: Int = 1,
    networkObserver: NetworkObserver,
    requestCall: suspend () -> Response<ResponseType>,
): ResponseType {
    checkConnection(networkObserver)

    val response = if (retryCount > 0) {
        retry(retryCount, networkObserver, requestCall)
    } else requestCall()

    return when (response.code()) {
        HTTP_OK, HTTP_CREATED -> response.body()!!
        else -> throw FailedRequest(response.code())
    }
}

/**
 * This function will throw an exception if response code is not 200/201
 */
suspend fun <ResponseType> successOrThrow(
    retryCount: Int = 1,
    networkObserver: NetworkObserver,
    requestCall: suspend () -> Response<ResponseType>,
) {
    checkConnection(networkObserver)

    val response = if (retryCount > 0) {
        retry(retryCount, networkObserver, requestCall)
    } else requestCall()

    if (response.code() != HTTP_OK && response.code() != HTTP_CREATED) {
        throw FailedRequest(response.code())
    }
}

private fun checkConnection(networkObserver: NetworkObserver) {
    if (networkObserver.connectType == ConnectionType.NOT_CONNECTED) {
        throw NoInternetConnectionException()
    }
}
