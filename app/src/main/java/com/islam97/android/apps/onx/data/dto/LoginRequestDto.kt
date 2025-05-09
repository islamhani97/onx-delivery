package com.islam97.android.apps.onx.data.dto

import com.google.gson.annotations.SerializedName
import com.islam97.android.apps.onx.domain.models.LoginRequest

data class LoginRequestDto(
    @SerializedName("Value") val value: LoginRequestValueDto
)

data class LoginRequestValueDto(
    @SerializedName("P_LANG_NO") val languageNumber: String,
    @SerializedName("P_DLVRY_NO") val deliveryNumber: String,
    @SerializedName("P_PSSWRD") val password: String,
)

fun LoginRequest.toDto(): LoginRequestDto {
    return LoginRequestDto(
        value = LoginRequestValueDto(
            languageNumber = languageNumber, deliveryNumber = deliveryNumber, password = password
        )
    )
}
