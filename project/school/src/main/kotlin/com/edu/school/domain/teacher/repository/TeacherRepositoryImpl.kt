package com.edu.school.domain.teacher.repository

import com.edu.school.domain.teacher.model.Teacher
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Primary
@Repository
class TeacherRepositoryImpl(
    private val database: TeacherDto,
) : TeacherRepository {


    override fun save(teacher: Teacher): Mono<Teacher> {
        return database.save(teacher)
    }

    override fun findById(id: Long): Mono<Teacher> {
        return database.findById(id)
    }

    override fun findAll(sort: Sort): Flux<Teacher> {
        return database.findAll(sort)
    }

    override fun deleteById(id: Long): Mono<Void> {
        return database.deleteById(id)
    }
}