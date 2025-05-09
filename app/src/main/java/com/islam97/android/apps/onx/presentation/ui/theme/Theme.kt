package com.islam97.android.apps.onx.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme =
    lightColorScheme(primary = Primary, secondary = Secondary, tertiary = Tertiary)
private val LocalAppColorScheme = staticCompositionLocalOf { AppColorScheme() }
val MaterialTheme.appColorScheme: AppColorScheme @Composable get() = LocalAppColorScheme.current

@Composable
fun ONXTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppColorScheme provides AppColorScheme()) {
        MaterialTheme(
            colorScheme = LightColorScheme, typography = Typography, content = content
        )
    }
}