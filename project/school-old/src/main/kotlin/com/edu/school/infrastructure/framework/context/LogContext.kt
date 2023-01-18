package com.edu.school.infrastructure.framework.context

import net.logstash.logback.marker.Markers
import org.slf4j.Marker

data class LogContext(val map: MutableMap<String, Any> = mutableMapOf()) {
    fun marker(): Marker = Markers.appendEntries(map)
}
