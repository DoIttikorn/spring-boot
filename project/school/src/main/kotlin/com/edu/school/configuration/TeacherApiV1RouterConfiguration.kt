package com.edu.school.configuration

import com.edu.school.domain.teacher.TeacherHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Configuration
class TeacherApiV1RouterConfiguration {
    @Bean
    fun teacherApiV1Router(
        teacherHandler: TeacherHandler
    ): RouterFunction<*> = router {
        "/api/teacher".nest {
            "/v1".nest {
                GET("", teacherHandler::getTeacherByQuery)
                POST("", teacherHandler::createTeacher)
                "/{teacherId}".nest {
                    PUT("", teacherHandler::updateTeacherById)
                    DELETE("", teacherHandler::removeTeacherById)
                }
            }
        }
    }
}