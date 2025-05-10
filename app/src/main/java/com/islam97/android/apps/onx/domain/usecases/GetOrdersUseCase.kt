package com.islam97.android.apps.onx.domain.usecases

import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrdersRequest
import com.islam97.android.apps.onx.domain.repositories.AppRepository
import com.islam97.android.apps.onx.presentation.orders.OrdersFilter
import com.islam97.android.apps.onx.presentation.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOrdersUseCase
@Inject constructor(
    private val repository: AppRepository,
) {
    private var isOrdersLoadedFromApi = false

    @Suppress("UNCHECKED_CAST")
    suspend operator fun invoke(
        deliveryNumber: String, languageNumber: String, filter: OrdersFilter
    ): Flow<Result> {
        return flow {
            emit(Result.Loading)
            emit(
                try {
                    if (!isOrdersLoadedFromApi) {
                        repository.getOrdersFromRemote(
                            OrdersRequest(
                                deliveryNumber = deliveryNumber, languageNumber = languageNumber
                            )
                        ).collectLatest {
                            when (it) {
                                is Result.Success<*> -> {
                                    repository.deleteAllOrders()
                                    repository.saveOrders(it.data as List<Order>)
                                    isOrdersLoadedFromApi = true
                                }

                                else -> {}
                            }
                        }
                    }
                    when (filter) {
                        OrdersFilter.NEW -> {
                            Result.Success("", repository.getNewOrders())
                        }

                        OrdersFilter.OTHERS -> {
                            Result.Success("", repository.getOtherOrders())
                        }
                    }

                } catch (t: Throwable) {
                    Result.Error("")
                })
        }
    }
}