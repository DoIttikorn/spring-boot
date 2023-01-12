package com.edu.school.infrastructure.framework


import com.edu.school.infrastructure.framework.json.JsonMapper
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono
import java.lang.reflect.Type

fun ServerResponse.BodyBuilder.jsonBody(jsonMapper: JsonMapper, data: Any, dataType: Type): Mono<ServerResponse> {
    return this.json().body(BodyInserters.fromValue(jsonMapper.toJson(data, dataType)))
}

inline fun <reified T> ServerResponse.BodyBuilder.jsonBody(jsonMapper: JsonMapper, data: T): Mono<ServerResponse> {
    return this.json().body(BodyInserters.fromValue(jsonMapper.toJson(data, T::class.java)))
}