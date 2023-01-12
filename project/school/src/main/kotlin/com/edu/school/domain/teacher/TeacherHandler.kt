package com.edu.school.domain.teacher

import com.edu.school.infrastructure.framework.web.handler.HandlerPattern.add
import com.edu.school.infrastructure.framework.web.handler.HandlerPattern.find
import com.edu.school.infrastructure.framework.web.handler.HandlerPattern.update
import com.edu.school.infrastructure.framework.web.mapper.ServerResponseMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TeacherHandler(
    private val serverResponseMapper: ServerResponseMapper,
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getTeacherByQuery(request: ServerRequest): Mono<ServerResponse> {
        return find(serverResponseMapper) {
            Mono.just("get Teacher")
        }
    }

    fun createTeacher(request: ServerRequest): Mono<ServerResponse> {
        log.info("test create Teacher")
        return add(serverResponseMapper) {
            Mono.just("test create teacher")
        }
    }
    fun updateTeacherById(request: ServerRequest) :Mono<ServerResponse> {
        return update(serverResponseMapper) {
            Mono.just("update teacher")
        }
    }

    fun removeTeacherById(request: ServerRequest) : Mono<ServerResponse> {
        return update(serverResponseMapper) {
            Mono.just("delete teacher success")
        }
    }
}
