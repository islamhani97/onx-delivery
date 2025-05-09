package com.islam97.android.apps.onx.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam97.android.apps.onx.domain.models.LoginRequest
import com.islam97.android.apps.onx.domain.usecases.LoginUseCase
import com.islam97.android.apps.onx.presentation.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState: MutableStateFlow<Result?> = MutableStateFlow(null)
    val loginState: StateFlow<Result?> get() = _loginState

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            loginUseCase.invoke(request).collectLatest {
                _loginState.value = it
            }
        }
    }
}