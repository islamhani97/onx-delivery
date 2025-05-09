package com.islam97.android.apps.onx.domain.models

data class Order(
    val id: String, val status: OrderStatus, val totalPrice: String, val date: String
)

enum class OrderStatus {
    NEW, DELIVERING, DELIVERED, RETURNED
}

fun OrderStatus.toValue(): String {
    return name
}