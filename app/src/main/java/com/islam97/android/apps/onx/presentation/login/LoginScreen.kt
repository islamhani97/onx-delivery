package com.islam97.android.apps.onx.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Serializable
object RouteLoginScreen

@Composable
fun LoginScreen(navController: NavHostController) {
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = NavHostController(LocalContext.current))
}