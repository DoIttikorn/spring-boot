package com.edu.school

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("unit-test")
class SchoolApplicationTests {

    @Tag("SpringBootTest")
    @Test
    fun contextLoads() {
    }

}
