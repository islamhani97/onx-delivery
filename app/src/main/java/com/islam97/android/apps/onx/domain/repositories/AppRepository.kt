package com.islam97.android.apps.onx.domain.repositories

import com.islam97.android.apps.onx.domain.models.LoginRequest
import com.islam97.android.apps.onx.presentation.utils.Result
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun login(request: LoginRequest): Flow<Result>
}