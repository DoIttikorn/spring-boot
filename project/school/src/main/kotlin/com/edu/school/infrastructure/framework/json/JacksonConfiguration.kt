package com.edu.school.infrastructure.framework.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class JacksonConfiguration {

    @Bean("commonJacksonMapper")
    fun jsonMapper(): JsonMapper {
        return JsonMapperImpl(
            jacksonObject = ObjectMapper()
                .registerModule(
                    JavaTimeModule()
                        .addSerializer(
                            LocalDateTime::class.java,
                            LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )
                )
        )
    }
}