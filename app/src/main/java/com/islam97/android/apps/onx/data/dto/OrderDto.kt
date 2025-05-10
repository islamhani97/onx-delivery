package com.islam97.android.apps.onx.data.dto

import com.google.gson.annotations.SerializedName
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrderStatus
import java.util.Locale

data class OrderDto(
    @SerializedName("BILL_NO") val id: String,
    @SerializedName("DLVRY_STATUS_FLG") val statusFlag: String,
    @SerializedName("BILL_AMT") val totalAmount: String,
    @SerializedName("BILL_DATE") val date: String
)

fun OrderDto.toModel(): Order {
    return Order(
        id = id, status = when (statusFlag.toInt()) {
            1 -> OrderStatus.DELIVERING
            2 -> OrderStatus.DELIVERED
            3 -> OrderStatus.RETURNED
            else -> OrderStatus.NEW
        }, totalPrice = String.format(Locale("en"), "%.2f", totalAmount.toDouble()), date = date
    )
}