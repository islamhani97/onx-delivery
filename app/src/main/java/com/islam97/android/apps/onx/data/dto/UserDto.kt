package com.islam97.android.apps.onx.data.dto

import com.google.gson.annotations.SerializedName
import com.islam97.android.apps.onx.domain.models.User

data class UserDto(
    @SerializedName("DeliveryName") val name: String
)

fun UserDto.toModel(): User {
    return User(name = name)
}