package com.edu.school.infrastructure.framework.json

import com.edu.school.infrastructure.framework.json.exception.JsonMapperException
import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.reflect.Type

class JsonMapperImpl(private val jacksonObject: ObjectMapper) : JsonMapper {
    //  JSON to object
    override fun <T> fromJson(json: String, clazz: Class<T>): T {
        return fromJson { jacksonObject.readValue(json, clazz) }
    }

    //  Object to JSON for API
    override fun <T> toJson(data: T, clazz: Class<T>): String {
        return toJson { jacksonObject.writerFor(clazz).writeValueAsString(data) }
    }

    override fun <T> fromJson(json: String, type: Type ): T {
        val typeCon = jacksonObject.constructType(type)
        return fromJson { jacksonObject.readValue(json, typeCon) }
    }

    override fun <T> toJson(data: T, type: Type): String {
        val typeCon = jacksonObject.constructType(type)
        return toJson { jacksonObject.writerFor(typeCon).writeValueAsString(data) }
    }

    private fun toJson(block: () -> String?): String {
        return try {
            block.invoke()
        } catch (e: Exception) {
            when (val error = e.cause) {
                is JsonMapperException -> throw error
                else -> throw JsonMapperException(e.message ?: "toJson failed for unknown reson")
            }
        } ?: throw JsonMapperException("toJson returns null object")
    }

    private fun <T> fromJson(block: () -> T?): T {
        return try {
            block.invoke()
        } catch (e: Exception) {
            when (val error = e.cause) {
                is JsonMapperException -> throw error
                else -> throw JsonMapperException(e.message ?: "fromJson failed for unknown reason")
            }
        } ?: throw JsonMapperException("fromJson returns null object")
    }
}