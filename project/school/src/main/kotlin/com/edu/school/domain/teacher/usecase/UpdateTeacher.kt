package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.model.TeacherRequestBody
import com.edu.school.domain.teacher.repository.TeacherRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.LocalDateTime


@Service
class UpdateTeacher(
    private val repository: TeacherRepository
) {

    fun execute(teacherId: Long, teacherRequestBody: TeacherRequestBody): Mono<Teacher> {
        return repository.findById(teacherId)
            .map { teacher ->
                teacher.copy(
                    firstName = teacherRequestBody.firstName,
                    lastName = teacherRequestBody.lastName,
                    age = teacherRequestBody.age,
                    email = teacherRequestBody.email,
                    updatedAt = LocalDateTime.now(),
                )
            }
            .switchIfEmpty {
                Mono.error(Exception("Teacher not found"))
            }
            .flatMap { repository.save(it) }
    }


}