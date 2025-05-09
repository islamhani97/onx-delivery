package com.islam97.android.apps.onx.data.repositories

import com.islam97.android.apps.onx.data.dto.toDto
import com.islam97.android.apps.onx.data.dto.toModel
import com.islam97.android.apps.onx.data.remote.RemoteApi
import com.islam97.android.apps.onx.domain.models.LoginRequest
import com.islam97.android.apps.onx.domain.repositories.AppRepository
import com.islam97.android.apps.onx.presentation.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl
@Inject constructor(
    private val remoteApi: RemoteApi
) : BaseRepository(), AppRepository {
    override suspend fun login(request: LoginRequest): Flow<Result> {
        return callApi(apiCall = { remoteApi.login(request.toDto()) }) { it?.toModel() }
    }
}