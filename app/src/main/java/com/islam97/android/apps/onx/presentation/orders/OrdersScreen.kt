package com.islam97.android.apps.onx.presentation.orders

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Serializable
object RouteOrdersScreen

@Composable
fun OrdersScreen(navController: NavHostController) {
}

@Preview(showBackground = true)
@Composable
fun PreviewOrdersScreen() {
    OrdersScreen(navController = NavHostController(LocalContext.current))
}