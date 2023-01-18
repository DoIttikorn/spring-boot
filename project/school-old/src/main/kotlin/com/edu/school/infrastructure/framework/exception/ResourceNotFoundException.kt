package com.edu.school.infrastructure.framework.exception

class ResourceNotFoundException : ApplicationException("Resource not found", "A specified resource couldn't be found on this server")