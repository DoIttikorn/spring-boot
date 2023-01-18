package com.edu.school.infrastructure.framework.exception

sealed class InvalidRequestException(description: String) : ApplicationException("Invalid Request Error", description) {
    class MissingArgumentException(argumentName: String) : InvalidRequestException("`$argumentName` is missing!")
    class InvalidArgumentException(argumentName: String) : InvalidRequestException("`$argumentName` is invalid!")
    class MissingRequestBodyException : InvalidRequestException("Request body is required!")
    class InvalidRequestBodyException(description: String? = null) : InvalidRequestException("Request body is incorrect! $description")
}