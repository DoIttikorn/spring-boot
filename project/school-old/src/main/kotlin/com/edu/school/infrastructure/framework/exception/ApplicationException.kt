package com.edu.school.infrastructure.framework.exception

abstract class ApplicationException(
    val title:String,
    val description: String
): Exception("$title , $description")