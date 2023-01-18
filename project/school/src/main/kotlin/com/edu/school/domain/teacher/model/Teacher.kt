package com.edu.school.domain.teacher.model

import com.edu.school.infrastructure.database.model.Status
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("teacher")
data class Teacher(
    @Id
    var id: Long = 0,
    var firstName: String,
    var lastName: String,
    var age: Int?,
    var email: String?,
    var status: String = Status.ACTIVE.value,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)

//@JsonFormat
//        (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.sss")

// @JsonSerialize
//        (using = DateFormat.Serializer::class)
//    @JsonDeserialize
//        (using = DateFormat.Deserializer::class)