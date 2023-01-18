package com.edu.school.infrastructure.framework.exception


abstract class ClientServerException(
    val type: ClientServerExceptionType,
    title: String,
    description: String
) : ApplicationException(title, description)