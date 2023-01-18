package com.edu.school.infrastructure.database.configuration

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories


//@Configuration
//@EnableR2dbcRepositories
//class DatabaseConfig : AbstractR2dbcConfiguration() {
//    override fun connectionFactory(): ConnectionFactory {
//        return PostgresqlConnectionFactory(
//            PostgresqlConnectionConfiguration.builder()
//                .host("localhost")
//                .database("test")
//                .username("user")
//                .password("password")
//                .codecRegistrar(EnumCodec.builder().withEnum("post_status", Post.Status::class.java).build())
//                .build()
//        )
//    }
//
//}
//