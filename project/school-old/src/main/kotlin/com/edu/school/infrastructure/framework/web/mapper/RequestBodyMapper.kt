package com.edu.school.infrastructure.framework.web.mapper

import com.edu.school.infrastructure.framework.context.LogContext
import com.edu.school.infrastructure.framework.exception.InvalidRequestException.InvalidRequestBodyException
import com.edu.school.infrastructure.framework.exception.InvalidRequestException.MissingRequestBodyException
import com.edu.school.infrastructure.framework.json.JsonMapper
import com.edu.school.infrastructure.framework.json.exception.JsonMapperException
import com.edu.school.infrastructure.framework.web.configuration.WebConstant.MARKER_REQUEST_BODY
import net.logstash.logback.marker.Markers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.lang.reflect.Type

@Component
class RequestBodyMapper {

    private val log = LoggerFactory.getLogger(javaClass)

    fun <T> map(
        request: ServerRequest,
        type: Type,
        jsonMapper: JsonMapper,
        logContext: LogContext? = null
    ): Mono<T> {
        return request.bodyToMono<String>()
            .switchIfEmpty {
                Mono.error(MissingRequestBodyException())
            }.flatMap { requestBody ->
                Mono.create { sink ->
                    try {
                        val result = jsonMapper.fromJson<T>(requestBody, type)
                        sink.success(result)
                    } catch (e: JsonMapperException) {
                        val exception = InvalidRequestBodyException(e.message)
                        val marker = if (logContext != null) {
                            logContext.map[MARKER_REQUEST_BODY] = requestBody
                            logContext.marker()
                        } else {
                            Markers.append(MARKER_REQUEST_BODY, requestBody)
                        }
                        log.error(marker, exception.message)
                        sink.error(exception)
                    }
                }
            }
    }
}