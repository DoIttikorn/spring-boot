package com.edu.school.domain.teacher.repository

import com.edu.school.domain.teacher.model.Teacher
import org.springframework.data.domain.Sort
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TeacherRepository {
    fun save(teacher: Teacher): Mono<Teacher>
    fun findById(id: Long): Mono<Teacher>
    fun findAll(sort: Sort): Flux<Teacher>
}