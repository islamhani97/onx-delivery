package com.islam97.android.apps.onx.domain.models

import com.islam97.android.apps.onx.R

data class Order(
    val id: String, val status: OrderStatus, val totalPrice: String, val date: String
)

enum class OrderStatus(val textResourceId: Int) {
    NEW(R.string.order_status_new), DELIVERING(R.string.order_status_delivering), DELIVERED(R.string.order_status_delivered), RETURNED(
        R.string.order_status_returned
    )
}