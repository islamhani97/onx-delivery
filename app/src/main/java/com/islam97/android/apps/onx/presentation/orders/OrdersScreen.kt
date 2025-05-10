package com.islam97.android.apps.onx.presentation.orders

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.presentation.login.RouteLoginScreen
import com.islam97.android.apps.onx.presentation.ui.composeables.CustomSingleChoiceSegmentedRow
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import com.islam97.android.apps.onx.presentation.utils.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

private const val SESSION_TIME_OUT = (2 * 60 * 1000).toLong()

@Serializable
data class RouteOrdersScreen(val deliveryId: String, val userName: String)

@Suppress("UNCHECKED_CAST")
@Composable
fun OrdersScreen(navController: NavHostController, backStackEntry: NavBackStackEntry) {
    val context = LocalContext.current
    val viewModel: OrdersViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    var isLoading by remember { mutableStateOf(false) }
    var orders by remember { mutableStateOf(listOf<Order>()) }

    val filters = OrdersFilter.entries.toList()
    var selectedFilterIndex by remember { mutableIntStateOf(0) }

    val route = backStackEntry.toRoute<RouteOrdersScreen>()
    var job by remember { mutableStateOf<Job?>(null) }


    fun startInactivityTimer() {
        job?.cancel()
        job = coroutineScope.launch {
            delay(SESSION_TIME_OUT)
            Toast.makeText(
                context, context.getString(R.string.message_session_timeout), Toast.LENGTH_SHORT
            ).show()
            navController.navigate(
                RouteLoginScreen,
                navOptions { popUpTo(RouteOrdersScreen("", "")) { inclusive = true } })
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    startInactivityTimer()
                }

                Lifecycle.Event.ON_STOP -> {
                    startInactivityTimer()
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            job?.cancel()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getOrders(route.deliveryId, "1", filters[selectedFilterIndex])
        viewModel.ordersState.collectLatest {
            when (it) {
                is Result.Loading -> {
                    isLoading = true
                }

                is Result.Success<*> -> {
                    isLoading = false
                    orders = it.data as List<Order>
                }

                is Result.Error -> {
                    isLoading = false
                }

                else -> {
                    isLoading = false
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitPointerEvent()
                        startInactivityTimer()
                    }
                }
            }

    ) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            val (topBannerReference, topCornerShapeReference, languageButtonReference, userNameReference, deliveryManReference, loadingIndicatorReference, filterReference, ordersListReference, emptyViewReference) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(
                        topBannerReference
                    ) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(144.dp)
                    .background(MaterialTheme.appColorScheme.error))
            Box(
                modifier = Modifier
                    .constrainAs(
                        topCornerShapeReference
                    ) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .size(240.dp)
                    .offset(96.dp, (-96).dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.appColorScheme.primary))

            IconButton(modifier = Modifier
                .constrainAs(languageButtonReference) {
                    top.linkTo(topBannerReference.top)
                    end.linkTo(topBannerReference.end)
                    bottom.linkTo(topBannerReference.bottom)
                }

                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.appColorScheme.white)
                .size(32.dp), onClick = {

            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_language),
                    contentDescription = "",
                    tint = MaterialTheme.appColorScheme.primary
                )
            }
            Text(modifier = Modifier
                .constrainAs(userNameReference) {
                    top.linkTo(topBannerReference.top)
                    start.linkTo(topBannerReference.start)
                    bottom.linkTo(topBannerReference.bottom)
                    width = Dimension.percent(0.5f)
                    verticalBias = 0.6f
                }
                .padding(start = 16.dp),
                text = route.userName,
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.appColorScheme.white))
            Icon(
                modifier = Modifier.constrainAs(deliveryManReference) {
                    start.linkTo(topBannerReference.start)
                    end.linkTo(topBannerReference.end)
                    bottom.linkTo(topBannerReference.bottom)
                    horizontalBias = 0.75f
                },
                painter = painterResource(id = R.mipmap.ic_delivery_man),
                contentDescription = "",
                tint = Color.Unspecified
            )

            CustomSingleChoiceSegmentedRow(
                modifier = Modifier
                    .constrainAs(
                        filterReference
                    ) {
                        top.linkTo(topBannerReference.bottom)
                        start.linkTo(topBannerReference.start)
                        end.linkTo(topBannerReference.end)
                    }
                    .padding(16.dp),
                options = filters.map { stringResource(it.textResourceId) },
                selectedOptionIndex = selectedFilterIndex
            ) {
                selectedFilterIndex = it
                viewModel.getOrders(route.deliveryId, "1", filters[selectedFilterIndex])

            }

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .constrainAs(
                                loadingIndicatorReference
                            ) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                            .size(60.dp))
                }

                orders.isEmpty() -> {
                    OrdersEmptyView(
                        modifier = Modifier.constrainAs(
                            emptyViewReference
                        ) {
                            top.linkTo(topBannerReference.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        })
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .constrainAs(
                                ordersListReference
                            ) {
                                top.linkTo(filterReference.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                height = Dimension.fillToConstraints
                            }
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        items(orders.size) { index ->
                            OrderItem(
                                modifier = Modifier
                                    .fillMaxWidth(0.95f)
                                    .padding(top = if (index != 0) 12.dp else 0.dp),
                                order = orders[index]
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrdersScreen() {
    OrdersScreen(
        navController = NavHostController(LocalContext.current),
        backStackEntry = NavHostController(LocalContext.current).currentBackStackEntry!!
    )
}