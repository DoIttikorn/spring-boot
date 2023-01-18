package com.edu.school.domain.teacher.repository

import com.edu.school.domain.teacher.model.Teacher
import com.edu.school.infrastructure.database.repository.RepositoryFramework
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Primary
@Repository
interface TeacherRepositoryImpl : TeacherRepository, RepositoryFramework<Teacher, Long> {

}