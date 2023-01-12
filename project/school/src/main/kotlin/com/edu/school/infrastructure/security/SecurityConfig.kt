package com.edu.school.infrastructure.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
//        TODO: Disable auth
        return http
            .csrf().disable()
            .headers { headers ->
                headers
                    //Not allow to load the page in any iFrame or any site
                    .frameOptions { frameOptions ->
                        frameOptions.mode(XFrameOptionsServerHttpHeadersWriter.Mode.DENY)
                    }
                    //Allow scripting source on itself only
                    .contentSecurityPolicy { contentSecurityPolicy ->
                        contentSecurityPolicy.policyDirectives("script-src 'self'")
                    }
            }
            .build();
    }
}

//.authorizeExchange()
//            .anyExchange().authenticated()
//            .and()