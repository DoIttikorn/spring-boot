package com.edu.school.domain.teacher.repository

import com.edu.school.domain.teacher.model.Teacher
import reactor.core.publisher.Mono

interface TeacherRepository {
    fun save(teacher: Teacher): Mono<Teacher>

}