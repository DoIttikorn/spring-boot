package com.edu.school.domain.teacher.model

import com.fasterxml.jackson.annotation.JsonProperty


data class TeacherRequestBody(
    @JsonProperty("first name") val firstName: String,
    @JsonProperty("last name") val lastName: String,
    @JsonProperty("age") val age: Int,
)
