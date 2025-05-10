package com.islam97.android.apps.onx.presentation.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.domain.usecases.GetOrdersUseCase
import com.islam97.android.apps.onx.presentation.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel
@Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {

    private val _ordersState: MutableStateFlow<Result?> = MutableStateFlow(null)
    val ordersState: StateFlow<Result?> get() = _ordersState

    fun getOrders(
        deliveryNumber: String, languageNumber: String, filter: OrdersFilter
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getOrdersUseCase.invoke(deliveryNumber, languageNumber, filter).collectLatest {
                _ordersState.value = it
            }
        }
    }
}

enum class OrdersFilter(val textResourceId: Int) {
    NEW(R.string.order_filter_new), OTHERS(R.string.order_filter_others)
}