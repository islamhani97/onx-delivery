package com.islam97.android.apps.onx.domain.repositories

import com.islam97.android.apps.onx.domain.models.LoginRequest
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrdersRequest
import com.islam97.android.apps.onx.presentation.utils.Result
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun login(request: LoginRequest): Flow<Result>
    suspend fun getOrdersFromRemote(request: OrdersRequest): Flow<Result>
    suspend fun saveOrders(orders: List<Order>)
    suspend fun deleteAllOrders()
    suspend fun getNewOrders(): List<Order>
    suspend fun getOtherOrders(): List<Order>
}