package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.model.TeacherRequestBody
import com.edu.school.domain.teacher.repository.TeacherRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CreateTeacher(
    private val repository: TeacherRepository
) {
    fun execute(teacherRequestBody: TeacherRequestBody): Mono<Teacher> {
        val teacher = Teacher(
            firstName = teacherRequestBody.firstName,
            lastName = teacherRequestBody.lastName,
            age = teacherRequestBody.age,
            email = teacherRequestBody.email,
            created_at = null,
            updated_at = null
        )
        return repository.save(teacher)
    }


}