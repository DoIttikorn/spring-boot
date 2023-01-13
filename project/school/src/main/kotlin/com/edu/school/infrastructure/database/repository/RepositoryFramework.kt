package com.edu.school.infrastructure.database.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository

interface RepositoryFramework<T, ID> : R2dbcRepository<T, ID> {}