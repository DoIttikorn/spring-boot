package com.edu.school.infrastructure.framework.web.handler

import com.edu.school.infrastructure.framework.exception.ClientServerException
import com.edu.school.infrastructure.framework.exception.ClientServerExceptionType.CLIENT_ERROR
import com.edu.school.infrastructure.framework.exception.ClientServerExceptionType.THIRD_PARTY_ERROR
import com.edu.school.infrastructure.framework.exception.InvalidRequestException
import com.edu.school.infrastructure.framework.exception.ResourceNotFoundException
import com.edu.school.infrastructure.framework.web.mapper.ServerResponseMapper
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.lang.reflect.Type
import java.text.ParseException

object HandlerPattern {

    inline fun <reified T> find(
        serverResponseMapper: ServerResponseMapper,
        responseBodyType: Type = T::class.java,
        crossinline handler: () -> Mono<T>
    ): Mono<ServerResponse> {
        return handler()
            .flatMap { responseBody ->
                serverResponseMapper.responseMono(HttpStatus.OK, responseBody, responseBodyType)
            }.onErrorResume(InvalidRequestException::class.java) {
                serverResponseMapper.error(HttpStatus.BAD_REQUEST, it)
            }.onErrorResume(ParseException::class.java) {
                serverResponseMapper.error(HttpStatus.BAD_REQUEST, it)
            }.onErrorResume(NoSuchElementException::class.java) {
                serverResponseMapper.error(HttpStatus.NOT_FOUND, it)
            }.onErrorResume(ResourceNotFoundException::class.java) {
                serverResponseMapper.error(HttpStatus.NOT_FOUND, it)
            }.onErrorResume {
                errorResponse(serverResponseMapper, it)
            }
    }

    inline fun <reified T> add(
        serverResponseMapper: ServerResponseMapper,
        responseBodyType: Type = T::class.java,
        crossinline handler: () -> Mono<T>
    ): Mono<ServerResponse> {
        return handler()
            .flatMap {
                serverResponseMapper.responseMono(HttpStatus.CREATED, it, responseBodyType)
            }.onErrorResume(InvalidRequestException::class.java) {
                serverResponseMapper.error(HttpStatus.BAD_REQUEST, it)
            }.onErrorResume {
                errorResponse(serverResponseMapper, it)
            }
    }

    inline fun <reified T> update(
        serverResponseMapper: ServerResponseMapper,
        responseBodyType: Type = T::class.java,
        crossinline handler: () -> Mono<T>
    ): Mono<ServerResponse> {
        return handler()
            .flatMap {
                serverResponseMapper.responseMono(HttpStatus.OK, it, responseBodyType)
            }.onErrorResume(InvalidRequestException::class.java) {
                serverResponseMapper.error(HttpStatus.BAD_REQUEST, it)
            }.onErrorResume(ResourceNotFoundException::class.java) {
                serverResponseMapper.error(HttpStatus.NOT_FOUND, it)
            }.onErrorResume {
                errorResponse(serverResponseMapper, it)
            }
    }

    fun errorResponse(serverResponseMapper: ServerResponseMapper, it: Throwable): Mono<ServerResponse> {
        return when {
            it is ClientServerException && it.type == CLIENT_ERROR -> {
                serverResponseMapper.error(HttpStatus.CONFLICT, it)
            }

            it is ClientServerException && it.type == THIRD_PARTY_ERROR -> {
                serverResponseMapper.error(HttpStatus.SERVICE_UNAVAILABLE, it)
            }

            else -> {
                serverResponseMapper.error(HttpStatus.INTERNAL_SERVER_ERROR, it)
            }
        }
    }
}


