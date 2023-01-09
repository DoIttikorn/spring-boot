package com.edu.school.domain.teacher.configuration

import com.edu.school.domain.teacher.TeacherHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Configuration
class TeacherApiV1RouterConfiguration {
    @Bean
    fun TeacherApiV1Router(
        teacherHandler: TeacherHandler
    ): RouterFunction<*> = router {
        "/api/teacher/v1"
            .and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON))
            .nest {
                "".nest {
                    GET("", teacherHandler::getTeacher)
                }
            }
    }
}