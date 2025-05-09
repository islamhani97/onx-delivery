package com.islam97.android.apps.onx.data.remote

import com.islam97.android.apps.onx.data.dto.LoginRequestDto
import com.islam97.android.apps.onx.data.dto.Response
import com.islam97.android.apps.onx.data.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteApi {
    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    suspend fun login(@Body request: LoginRequestDto): Response<UserDto>
}