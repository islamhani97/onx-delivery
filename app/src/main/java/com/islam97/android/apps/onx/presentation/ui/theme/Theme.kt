package com.islam97.android.apps.onx.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,
    background = White,
    surface = White

)
private val LocalAppColorScheme = staticCompositionLocalOf { AppColorScheme() }
val MaterialTheme.appColorScheme: AppColorScheme @Composable get() = LocalAppColorScheme.current

private val LocalAppShapes = staticCompositionLocalOf { AppShapes() }
val MaterialTheme.appShapes: AppShapes @Composable get() = LocalAppShapes.current

@Composable
fun ONXTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalAppColorScheme provides AppColorScheme(), LocalAppShapes provides AppShapes()
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme, typography = Typography, content = content
        )
    }
}