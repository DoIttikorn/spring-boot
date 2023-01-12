package com.edu.school.infrastructure.framework.web.mapper

import com.edu.school.infrastructure.framework.exception.ApplicationException
import com.edu.school.infrastructure.framework.json.JsonMapper
import com.edu.school.infrastructure.framework.jsonBody
import com.edu.school.infrastructure.framework.web.model.ServerResponseError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.lang.reflect.Type

@Component
class ServerResponseMapper {

    @Autowired
    private lateinit var jsonMapper: JsonMapper

    fun responseMono(
        status: HttpStatus,
        responseBody: Any? = null,
        responseBodyType: Type? = null,
        cookies: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap()
    ): Mono<ServerResponse> {
        val bodyBuilder = ServerResponse.status(status).apply {
            cookies.forEach { (key, value) ->
                cookie(
                    ResponseCookie.from(key, value)
                        .path("/")
                        .httpOnly(true)
                        .sameSite("Strict")
                        .secure(true)
                        .build()
                )
            }
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
        return when {
            responseBody == null || responseBody is Unit -> bodyBuilder.build()
            responseBodyType != null -> bodyBuilder.jsonBody(jsonMapper, responseBody, responseBodyType)
            else -> bodyBuilder.jsonBody(jsonMapper, responseBody)
        }
    }

    fun error(
        status: HttpStatus,
        throwable: Throwable,
        cookies: Map<String, String> = emptyMap()
    ): Mono<ServerResponse> {
        val data = when (throwable) {
            is ApplicationException -> ServerResponseError(status.value(), throwable.title, throwable.description)
            else -> ServerResponseError(
                status.value(), "Unknown Error", throwable.message
                    ?: "Unknown cause!"
            )
        }
        return responseMono(status, data, cookies = cookies)
    }

}