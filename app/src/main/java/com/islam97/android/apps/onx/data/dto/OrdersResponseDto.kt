package com.islam97.android.apps.onx.data.dto

import com.google.gson.annotations.SerializedName

data class OrdersResponseDto(
    @SerializedName("DeliveryBills") val orders: List<OrderDto>
)