package com.islam97.android.apps.onx.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OrderEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ordersDao(): OrdersDao
}