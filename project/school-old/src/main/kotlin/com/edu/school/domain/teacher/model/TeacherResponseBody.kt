package com.edu.school.domain.teacher.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class TeacherResponseBody(
    @JsonProperty("teacher id")
    var id: UUID,
    @JsonProperty("first name")
    var firstName: String,
    @JsonProperty("last name")
    var lastName: String,
    @JsonProperty("age")
    var age: Int
)
