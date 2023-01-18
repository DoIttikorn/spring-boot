package com.edu.school.domain.teacher.model

import com.fasterxml.jackson.annotation.JsonProperty


data class TeacherRequestBody(
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
    @JsonProperty("age") val age: Int?,
    @JsonProperty("email") val email: String?,
)