package com.islam97.android.apps.onx.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrdersDao {
    @Insert
    fun insertAll(orders: List<OrderEntity>)

    @Query("SELECT * FROM OrderEntity WHERE status = 'NEW'")
    fun getNewOrders(): List<OrderEntity>

    @Query("SELECT * FROM OrderEntity WHERE status != 'NEW'")
    fun getOtherOrders(): List<OrderEntity>

    @Query("DELETE FROM OrderEntity")
    fun deleteAll()
}