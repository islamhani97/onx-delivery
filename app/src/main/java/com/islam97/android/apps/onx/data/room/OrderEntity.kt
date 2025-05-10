package com.islam97.android.apps.onx.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrderStatus

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "total_price") val totalPrice: String,
    @ColumnInfo(name = "date") val date: String,
)

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        uid = 0L, id = id, status = status.name, totalPrice = totalPrice, date = date
    )
}

fun OrderEntity.toModel(): Order {
    return Order(
        id = id, status = OrderStatus.valueOf(status), totalPrice = totalPrice, date = date
    )
}