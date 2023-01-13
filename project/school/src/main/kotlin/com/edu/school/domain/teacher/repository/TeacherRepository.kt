package com.edu.school.domain.teacher.repository

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import com.edu.school.infrastructure.database.table.Teacher

@Repository
interface TeacherRepository {
    fun findByFirstName(): Mono<Teacher>
}