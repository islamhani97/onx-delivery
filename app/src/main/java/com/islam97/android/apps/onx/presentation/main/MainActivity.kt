package com.islam97.android.apps.onx.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islam97.android.apps.onx.presentation.login.LoginScreen
import com.islam97.android.apps.onx.presentation.login.RouteLoginScreen
import com.islam97.android.apps.onx.presentation.orders.OrdersScreen
import com.islam97.android.apps.onx.presentation.orders.RouteOrdersScreen
import com.islam97.android.apps.onx.presentation.splash.RouteSplashScreen
import com.islam97.android.apps.onx.presentation.splash.SplashScreen
import com.islam97.android.apps.onx.presentation.ui.theme.ONXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { ONXTheme { MainNavigation() } }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteSplashScreen) {
        composable<RouteSplashScreen> {
            SplashScreen(navController)
        }

        composable<RouteLoginScreen> {
            LoginScreen(navController)
        }

        composable<RouteOrdersScreen> {
            OrdersScreen(navController, it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    ONXTheme { MainNavigation() }
}