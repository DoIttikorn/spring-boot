package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.model.TeacherRequestBody
import com.edu.school.domain.teacher.repository.TeacherRepository
import io.mockk.mockk
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.*

internal class CreateTeacherTest {
    private val teacherRepository = mockk<TeacherRepository>()
    private val subject = CreateTeacher(teacherRepository)

    @Test
    fun `execute success`() {
        val requestBody = TeacherRequestBody(
            firstName = "firstName",
            lastName = "lastName",
            age = 20,
            email = "email",
        )
        val excepted = Teacher(
            firstName = "firstName",
            lastName = "lastName",
            age = 20,
            email = "email",
            createdAt = Date(System.currentTimeMillis()),
            updatedAt = Date(System.currentTimeMillis()),
        )
        every { teacherRepository.save(any()) } returns Mono.just(excepted)

        StepVerifier.create(subject.execute(requestBody))
            .expectNext(excepted)
            .verifyComplete()

        verify { teacherRepository.save(any()) }
    }
}