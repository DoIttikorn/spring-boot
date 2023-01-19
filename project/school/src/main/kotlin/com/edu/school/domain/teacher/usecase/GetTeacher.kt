package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.domain.teacher.repository.TeacherRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GetTeacher(
    private val repository: TeacherRepository
) {
    fun executeWithId(id: Long): Mono<Teacher> = repository.findById(id)

    fun executeAll(): Flux<Teacher> = repository.findAll(Sort.by(Sort.Direction.ASC, "id"))
}