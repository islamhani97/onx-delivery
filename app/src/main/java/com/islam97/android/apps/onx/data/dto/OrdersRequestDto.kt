package com.islam97.android.apps.onx.data.dto

import com.google.gson.annotations.SerializedName
import com.islam97.android.apps.onx.domain.models.OrdersRequest

data class OrdersRequestDto(
    @SerializedName("Value") val value: OrdersRequestValueDto
)

data class OrdersRequestValueDto(
    @SerializedName("P_DLVRY_NO") val deliveryNumber: String,
    @SerializedName("P_LANG_NO") val languageNumber: String
)


fun OrdersRequest.toDto(): OrdersRequestDto {
    return OrdersRequestDto(
        value = OrdersRequestValueDto(
            languageNumber = languageNumber, deliveryNumber = deliveryNumber
        )
    )
}