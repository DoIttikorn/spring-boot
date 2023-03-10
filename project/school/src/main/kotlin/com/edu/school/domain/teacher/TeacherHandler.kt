package com.edu.school.domain.teacher

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.model.TeacherRequestBody
import com.edu.school.domain.teacher.usecase.CreateTeacher
import com.edu.school.domain.teacher.usecase.DeleteTeacher
import com.edu.school.domain.teacher.usecase.GetTeacher
import com.edu.school.domain.teacher.usecase.UpdateTeacher
import com.edu.school.infrastructure.framework.json.JsonMapper
import com.edu.school.infrastructure.framework.utils.KTuple2
import com.edu.school.infrastructure.framework.web.handler.HandlerPattern.add
import com.edu.school.infrastructure.framework.web.handler.HandlerPattern.find
import com.edu.school.infrastructure.framework.web.handler.HandlerPattern.update
import com.edu.school.infrastructure.framework.web.mapper.RequestBodyMapper
import com.edu.school.infrastructure.framework.web.mapper.ServerResponseMapper
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class TeacherHandler(
    private val createTeacher: CreateTeacher,
    private val updateTeacher: UpdateTeacher,
    private val deleteTeacher: DeleteTeacher,
    private val jsonMapper: JsonMapper,
    private val requestBodyMapper: RequestBodyMapper,
    private val serverResponseMapper: ServerResponseMapper,
    private val getTeacher: GetTeacher,
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getTeacherAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(getTeacher.executeAll(), Teacher::class.java)
            .doFirst { log.info("start getTeacherAll") }
    }


    fun getTeacherById(request: ServerRequest): Mono<ServerResponse> {
        return find(serverResponseMapper) {
            request.pathVariable("teacherId").toLong().toMono()
                .doFirst { log.info("get teacher start") }
                .flatMap { teacherId -> getTeacher.executeWithId(teacherId) }
                .doOnNext { log.info("get teacher end") }
        }
    }

    fun createTeacher(request: ServerRequest): Mono<ServerResponse> {
        return add(serverResponseMapper) {
            requestBodyMapper.map<TeacherRequestBody>(request, TeacherRequestBody::class.java, jsonMapper)
                .doFirst { log.info("create teacher start") }
                .flatMap { requestBody -> createTeacher.execute(requestBody) }
                .doOnNext { log.info("create teacher end") }
        }
    }

    fun updateTeacherById(request: ServerRequest): Mono<ServerResponse> {
        return update(serverResponseMapper) {
            request.pathVariable("teacherId").toLong().toMono()
                .doFirst { log.info("update teacher start") }
                .flatMap { teacherId ->
                    Mono.zip(
                        teacherId.toMono(),
                        requestBodyMapper.map<TeacherRequestBody>(request, TeacherRequestBody::class.java, jsonMapper),
                    )
                }
                .map {
                    KTuple2.from(it)
                }
                .flatMap { (teacherId, requestBody) ->
                    updateTeacher.execute(teacherId, requestBody)
                }
                .doOnNext { log.info("update teacher end") }
        }
    }

    fun removeTeacherById(request: ServerRequest): Mono<ServerResponse> {
        return update(serverResponseMapper) {
            request.pathVariable("teacherId").toLong().toMono()
                .doFirst { log.info("remove teacher start") }
                .flatMap { teacherId -> deleteTeacher.executeWithId(teacherId) }
                .doOnNext { log.info("remove teacher end") }
        }
    }
}
