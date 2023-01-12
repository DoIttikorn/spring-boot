package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.TeacherRequestBody
import com.edu.school.domain.teacher.model.TeacherResponseBody
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class CreateTeacher {
    //    TODO: replace database
    val data: HashMap<UUID, TeacherResponseBody> = hashMapOf()

    fun execute(requestBody: TeacherRequestBody): Mono<TeacherResponseBody> {
        val id = UUID.randomUUID()
        data[id] = TeacherResponseBody(
            id = UUID.randomUUID(),
            firstName = requestBody.firstName,
            lastName = requestBody.lastName,
            age = requestBody.age
        )

        return Mono.just(
            TeacherResponseBody(
                id = id,
                firstName = requestBody.firstName,
                lastName = requestBody.lastName,
                age = requestBody.age
            )
        )
    }
}