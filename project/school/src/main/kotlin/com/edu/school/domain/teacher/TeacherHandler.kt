package com.edu.school.domain.teacher

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TeacherHandler {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getTeacher(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(
            Mono.just("Hello, World!"),
            String::class.java
        )
    }
}
