package com.edu.school.infrastructure.framework.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfiguration {

    @Bean("commonJacksonMapper")
    fun jsonMapper(): JsonMapper {
        return JsonMapperImpl(
            jacksonObject = ObjectMapper()
        )
    }
}