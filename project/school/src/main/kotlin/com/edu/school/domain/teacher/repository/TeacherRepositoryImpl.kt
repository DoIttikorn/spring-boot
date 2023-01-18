package com.edu.school.domain.teacher.repository

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.infrastructure.database.repository.RepositoryFramework
import org.springframework.context.annotation.Primary
import org.springframework.data.r2dbc.repository.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Primary
@Repository
interface TeacherRepositoryImpl : TeacherRepository, RepositoryFramework<Teacher, Long> {

    @Query("SELECT * FROM teacher WHERE id = :id")
    override fun findById(id: Long): Mono<Teacher>
}