package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.repository.TeacherRepository
import com.edu.school.infrastructure.framework.exception.ResourceNotFoundException
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Sort
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDateTime

@DisplayName("Test get data teacher")
internal class GetTeacherTest {
    private val repository = mockk<TeacherRepository>()
    private val subject = GetTeacher(repository)

    @Test
    fun `executeWithId success`() {
        val teacher = Teacher(
            id = 1,
            firstName = "firstName",
            lastName = "lastName",
            age = 20,
            email = "email",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        every { repository.findById(1) } answers { Mono.just(teacher) }

        StepVerifier.create(subject.executeWithId(1)).expectNext(teacher).verifyComplete()
    }

    @Test
    fun `executeWithId fails when not found teacher by id`() {

        every { repository.findById(1) } answers { Mono.empty() }

        StepVerifier.create(subject.executeWithId(1)).verifyError(ResourceNotFoundException::class.java)
    }

    @Test
    fun `executeAll success`() {
        val teachers = Flux.just(
            Teacher(
                id = 1,
                firstName = "firstName",
                lastName = "lastName",
                age = 20,
                email = "email",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            ),
        )

        every { repository.findAll(Sort.by(Sort.Direction.ASC, "id")) } answers { teachers }

        StepVerifier.create(subject.executeAll())
            .expectNextMatches {
                assertThat(it.id).isEqualTo(1)
                assertThat(it.firstName).isEqualTo("firstName")
                assertThat(it.lastName).isEqualTo("lastName")
                assertThat(it.age).isEqualTo(20)
                assertThat(it.email).isEqualTo("email")
                assertThat(it.createdAt).isNotNull
                assertThat(it.updatedAt).isNotNull
                true
            }
            .verifyComplete()
    }
}