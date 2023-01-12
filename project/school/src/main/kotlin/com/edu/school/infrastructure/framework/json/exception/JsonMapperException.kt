package com.edu.school.infrastructure.framework.json.exception

import com.edu.school.infrastructure.framework.exception.ApplicationException

class JsonMapperException(description: String) : ApplicationException("Json Mapper Error",description)