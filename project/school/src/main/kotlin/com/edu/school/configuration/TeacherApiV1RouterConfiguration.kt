package com.edu.school.configuration

import com.edu.school.domain.student.StudentHandler
import com.edu.school.domain.teacher.TeacherHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Configuration
class TeacherApiV1RouterConfiguration {
    @Bean
    fun teacherApiV1Router(
        teacherHandler: TeacherHandler,
        studentHandler: StudentHandler
    ): RouterFunction<*> = router {
        "/api/teacher/v1".nest {
            GET("", teacherHandler::getTeacherAll)
            POST("", teacherHandler::createTeacher)
            "/{teacherId}".nest {
                GET("", teacherHandler::getTeacherById)
                PATCH("", teacherHandler::updateTeacherById)
                DELETE("", teacherHandler::removeTeacherById)
            }
//            student
            "/{teacherId}/student".nest {
                GET("", studentHandler::getStudentAll)
                POST("", studentHandler::createStudent)
                "/{studentId}".nest {
                    GET("", studentHandler::getStudentById)
                    PATCH("", studentHandler::updateStudentById)
                    DELETE("", studentHandler::removeStudentById)
                }
            }
        }
    }
}