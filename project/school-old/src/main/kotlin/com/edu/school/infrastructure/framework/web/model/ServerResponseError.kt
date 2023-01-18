package com.edu.school.infrastructure.framework.web.model

import com.edu.school.infrastructure.framework.json.JsonMapper
import com.edu.school.infrastructure.framework.web.exception.WebException

data class ServerResponseError(
    val status: Int,
    val error: String,
    val description: String
) {
    companion object {
        fun from(responseError: WebException.ResponseErrorException, jsonMapper: JsonMapper): ServerResponseError {
            return try {
                jsonMapper.fromJson(responseError.body ?: "", ServerResponseError::class.java)
            } catch (exception: Exception) {
                ServerResponseError(
                    status = responseError.httpStatus.value(),
                    error = responseError.httpStatus.name,
                    description = responseError.body ?: "Unknown cause"
                )
            }
        }
    }
}
