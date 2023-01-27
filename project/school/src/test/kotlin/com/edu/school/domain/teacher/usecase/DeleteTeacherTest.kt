package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.repository.TeacherRepository
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDateTime

@DisplayName("Test for DeleteTeacher")
internal class DeleteTeacherTest {
    private val repository = mockk<TeacherRepository>()
    private val subject = DeleteTeacher(repository)


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
        every { repository.deleteById(1) } answers { Mono.empty() }
        StepVerifier.create(subject.executeWithId(1))
            .verifyComplete()
    }

    @Test
    fun `executeWithId fail when no found by id`() {
        every { repository.findById(1) } answers { Mono.empty() }

        StepVerifier.create(subject.executeWithId(1))
            .verifyError()

        verify { repository.deleteById(any()) wasNot Called }
    }
}