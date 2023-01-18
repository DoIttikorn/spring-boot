package com.edu.school.domain.teacher.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("teacher")
data class Teacher(
    @Id
    var id: Int = 0,
    @Column("first_name")
    var firstName: String,
    @Column("last_name")
    var lastName: String,
    @Column("age")
    var age: Int?,
    var email: String?,
    @Column("created_at")
    var createdAt: Date,
    @Column("updated_at")
    var updatedAt: Date,
)

//@JsonFormat
//        (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.sss")

// @JsonSerialize
//        (using = DateFormat.Serializer::class)
//    @JsonDeserialize
//        (using = DateFormat.Deserializer::class)