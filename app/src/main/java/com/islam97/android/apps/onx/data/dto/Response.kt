package com.islam97.android.apps.onx.data.dto

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("Result") val result: ResponseResult, @SerializedName("Data") val data: T?
) {
    val isSuccessful: Boolean
        get() {
            return result.status == 0
        }
}

data class ResponseResult(
    @SerializedName("ErrNo") val status: Int?,
    @SerializedName("ErrMsg") val message: String?,
)