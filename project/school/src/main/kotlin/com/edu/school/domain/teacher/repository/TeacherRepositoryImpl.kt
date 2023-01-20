package com.edu.school.domain.teacher.repository

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.infrastructure.database.repository.RepositoryFramework
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Primary
@Repository
interface TeacherRepositoryImpl : TeacherRepository, RepositoryFramework<Teacher, Long> {

    @Query("SELECT * FROM teacher WHERE id = :id")
    override fun findById(id: Long): Mono<Teacher>

    @Query("SELECT * FROM teacher WHERE status != 'D' ORDER BY id ASC  ")
    override fun findAll(sort: Sort): Flux<Teacher>

    @Query("UPDATE teacher  SET status = 'D' WHERE id = :id")
    override fun deleteById(
        @Param("id") id: Long,
    ): Mono<Void>
}