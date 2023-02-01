package com.edu.school.domain.student

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class StudentHandler {

    fun getStudentAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("getStudentAll"), String::class.java)
    }
    fun createStudent(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("createStudent"), String::class.java)
    }

    fun getStudentById(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("getStudentById"), String::class.java)
    }

    fun updateStudentById(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("updateStudentById"), String::class.java)
    }

    fun removeStudentById(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("removeStudentById"), String::class.java)
    }

}