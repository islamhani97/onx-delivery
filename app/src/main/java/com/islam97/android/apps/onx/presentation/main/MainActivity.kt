package com.islam97.android.apps.onx.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.islam97.android.apps.onx.presentation.language.ChangeLanguageDialog
import com.islam97.android.apps.onx.presentation.language.Language
import com.islam97.android.apps.onx.presentation.login.LoginScreen
import com.islam97.android.apps.onx.presentation.login.RouteLoginScreen
import com.islam97.android.apps.onx.presentation.orders.OrdersScreen
import com.islam97.android.apps.onx.presentation.orders.RouteOrdersScreen
import com.islam97.android.apps.onx.presentation.start.StartActivity
import com.islam97.android.apps.onx.presentation.ui.theme.ONXTheme
import com.islam97.android.apps.onx.presentation.utils.LanguageDataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var languageDataStoreManager: LanguageDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            var languageDialogVisibilityState by remember { mutableStateOf(false) }
            var selectedLanguage by remember { mutableStateOf(Language.ARABIC) }

            LaunchedEffect(Unit) {
                selectedLanguage = languageDataStoreManager.getLanguage().first()
            }
            ONXTheme {
                MainNavigation(selectedLanguage) { languageDialogVisibilityState = true }
                if (languageDialogVisibilityState) {
                    ChangeLanguageDialog(selectedLanguage = selectedLanguage, onSelectLanguage = {
                        selectedLanguage = it
                    }, onApply = {
                        coroutineScope.launch(Dispatchers.IO) {
                            languageDataStoreManager.saveLanguage(selectedLanguage)
                        }
                        languageDialogVisibilityState = false
                        startActivity(Intent(this@MainActivity, StartActivity::class.java))
                        finish()
                    })
                }
            }
        }
    }

    @Composable
    fun MainNavigation(selectedLanguage: Language, onChangeLanguage: () -> Unit) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = RouteLoginScreen) {
            composable<RouteLoginScreen> {
                LoginScreen(navController, selectedLanguage, onChangeLanguage)
            }
            composable<RouteOrdersScreen> {
                OrdersScreen(navController, it, selectedLanguage, onChangeLanguage)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewMainActivity() {
        ONXTheme { MainNavigation(Language.ARABIC) {} }
    }
}