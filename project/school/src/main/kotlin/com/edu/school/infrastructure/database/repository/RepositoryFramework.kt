package com.edu.school.infrastructure.database.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface RepositoryFramework<T, ID> : R2dbcRepository<T, ID> {}