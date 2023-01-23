package com.edu.school.domain.teacher

import com.edu.school.configuration.TeacherApiV1RouterConfiguration
import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.usecase.CreateTeacher
import com.edu.school.domain.teacher.usecase.DeleteTeacher
import com.edu.school.domain.teacher.usecase.GetTeacher
import com.edu.school.domain.teacher.usecase.UpdateTeacher
import com.edu.school.infrastructure.framework.json.JsonMapperImpl
import com.edu.school.infrastructure.framework.web.mapper.RequestBodyMapper
import com.edu.school.infrastructure.framework.web.mapper.ServerResponseMapper
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList
import reactor.core.publisher.Flux
import java.time.LocalDateTime

internal class TeacherHandlerTest {
    private lateinit var client: WebTestClient
    private val createTeacher: CreateTeacher = mockk()
    private val updateTeacher: UpdateTeacher = mockk()
    private val deleteTeacher: DeleteTeacher = mockk()
    private val getTeacher = mockk<GetTeacher>()
    private val jsonMapper = JsonMapperImpl(ObjectMapper())
    private val serverResponseMapper = ServerResponseMapper(jsonMapper)
    private val requestBodyMapper = mockk<RequestBodyMapper>()

    @BeforeEach
    fun setUp() {
        val teacherHandler = TeacherHandler(createTeacher, updateTeacher, deleteTeacher, jsonMapper, requestBodyMapper, serverResponseMapper, getTeacher)
        val routes = TeacherApiV1RouterConfiguration().teacherApiV1Router(teacherHandler)
        client = WebTestClient.bindToRouterFunction(routes).build()
    }


    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getTeacherAll() {
        val teacherModel = Teacher(
            firstName = "firstName",
            lastName = "lastName",
            age = 1,
            email = "email",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        every { getTeacher.executeAll() } returns Flux.just(teacherModel)


        client.get().uri("/api/teacher/v1")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<Teacher>()
            .hasSize(1)
            .contains(teacherModel)

    }

//    @Test
//    fun getTeacherById() {
//    }
//
//    @Test
//    fun createTeacher() {
//    }
//
//    @Test
//    fun updateTeacherById() {
//    }
//
//    @Test
//    fun removeTeacherById() {
//    }
}