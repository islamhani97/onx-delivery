package com.islam97.android.apps.onx.data.repositories

import com.islam97.android.apps.onx.data.dto.toDto
import com.islam97.android.apps.onx.data.dto.toModel
import com.islam97.android.apps.onx.data.remote.RemoteApi
import com.islam97.android.apps.onx.data.room.AppDatabase
import com.islam97.android.apps.onx.data.room.toEntity
import com.islam97.android.apps.onx.data.room.toModel
import com.islam97.android.apps.onx.domain.models.LoginRequest
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrdersRequest
import com.islam97.android.apps.onx.domain.repositories.AppRepository
import com.islam97.android.apps.onx.presentation.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl
@Inject constructor(
    private val remoteApi: RemoteApi, private val appDatabase: AppDatabase
) : BaseRepository(), AppRepository {
    override suspend fun login(request: LoginRequest): Flow<Result> {
        return callApi(apiCall = { remoteApi.login(request.toDto()) }) { it?.toModel() }
    }

    override suspend fun getOrdersFromRemote(request: OrdersRequest): Flow<Result> {
        return callApi(apiCall = { remoteApi.getOrders(request.toDto()) }) { response -> response?.orders?.map { it.toModel() } }
    }

    override suspend fun saveOrders(orders: List<Order>) {
        appDatabase.ordersDao().insertAll(orders.map { it.toEntity() })
    }

    override suspend fun deleteAllOrders() {
        appDatabase.ordersDao().deleteAll()
    }

    override suspend fun getNewOrders(): List<Order> {
        return appDatabase.ordersDao().getNewOrders().map { it.toModel() }
    }

    override suspend fun getOtherOrders(): List<Order> {
        return appDatabase.ordersDao().getOtherOrders().map { it.toModel() }
    }
}