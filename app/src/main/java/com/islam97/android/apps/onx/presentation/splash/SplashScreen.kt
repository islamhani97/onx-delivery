package com.islam97.android.apps.onx.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.presentation.login.RouteLoginScreen
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object RouteSplashScreen

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(RouteLoginScreen, navOptions {
            popUpTo(
                RouteSplashScreen
            ) { inclusive = true }
        })
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.appColorScheme.tertiaryExtraLight)
                .padding(innerPadding)
        ) {
            val (splashViewReference, logoReference, deliveryBikeReference) = createRefs()
            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(splashViewReference) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        verticalBias = 0.9f
                    },
                painter = painterResource(id = R.drawable.ic_splash_view),
                contentDescription = "",
                tint = Color.Unspecified
            )

            Icon(
                modifier = Modifier.constrainAs(logoReference) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    verticalBias = 0.4f
                },
                painter = painterResource(id = R.drawable.ic_onx_logo),
                contentDescription = "",
                tint = Color.Unspecified
            )

            Icon(
                modifier = Modifier.constrainAs(deliveryBikeReference) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    verticalBias = 0.95f
                },
                painter = painterResource(id = R.drawable.ic_delivery_bike),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}