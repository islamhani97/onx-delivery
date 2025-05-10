package com.islam97.android.apps.onx.presentation.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.islam97.android.apps.onx.presentation.main.MainActivity
import com.islam97.android.apps.onx.presentation.splash.SplashScreen
import com.islam97.android.apps.onx.presentation.ui.theme.ONXTheme
import com.islam97.android.apps.onx.presentation.utils.LanguageDataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity : ComponentActivity() {

    @Inject
    lateinit var languageDataStoreManager: LanguageDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            updateLocale(languageDataStoreManager.getLanguage().first().code)
        }
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                delay(2000)
                startActivity(Intent(this@StartActivity, MainActivity::class.java))
                finish()
            }
            SplashScreen()
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewMainActivity() {
        ONXTheme { SplashScreen() }
    }

    private fun updateLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = resources
        val displayMetrics = resources.displayMetrics
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, displayMetrics)
    }
}