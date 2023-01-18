package com.edu.school.infrastructure.framework.web.exception

import com.edu.school.infrastructure.framework.exception.ApplicationException
import org.springframework.http.HttpStatus

sealed class WebException(description:String) : ApplicationException("Web Error", description) {
    class ExchangeException(description:String? = null): WebException(description ?: "Unknown error from exchange")
    class ExchangeTimeoutException: WebException("Exchange timeout!")
    class ResponseSuccessException(description: String? = null) : WebException(description
        ?: "Unknown error from handling success response")

    class ResponseErrorException(
        val httpStatus: HttpStatus, val body: String? = null
    ) : WebException("statusCode=${httpStatus.value()}, body=$body")
}
