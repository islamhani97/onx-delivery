package com.islam97.android.apps.onx.domain.usecases

import com.islam97.android.apps.onx.domain.models.LoginRequest
import com.islam97.android.apps.onx.domain.repositories.AppRepository
import com.islam97.android.apps.onx.presentation.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase
@Inject constructor(private val repository: AppRepository) {
    suspend operator fun invoke(request: LoginRequest): Flow<Result> {
        return repository.login(request)
    }
}