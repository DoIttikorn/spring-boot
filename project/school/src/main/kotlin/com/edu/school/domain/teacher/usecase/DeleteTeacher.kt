package com.edu.school.domain.teacher.usecase

import com.edu.school.domain.teacher.repository.TeacherRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty


@Service
class DeleteTeacher(
    private val repository: TeacherRepository,
) {
    fun executeWithId(id: Long): Mono<Void> {
        return repository.findById(id)
            .switchIfEmpty { Mono.error(RuntimeException("Teacher not found")) }
            .flatMap { repository.deleteById(id) }
    }
}