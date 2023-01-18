package com.edu.school.infrastructure.framework.json

import java.lang.reflect.Type

interface JsonMapper {
    fun <T> fromJson(json: String, clazz: Class<T>): T
    fun <T> toJson(data: T, clazz: Class<T>): String
    fun <T> fromJson(json: String, type: Type): T
    fun <T> toJson(data: T, type: Type): String
}