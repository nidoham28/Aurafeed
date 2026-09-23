package com.nidoham.aurafeed

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nidoham.aurafeed.core.navigation.Shell
import com.nidoham.aurafeed.core.navigation.Splash
import com.nidoham.aurafeed.core.theme.AppTheme
import com.nidoham.aurafeed.features.shell.pages.ShellPage
import com.nidoham.aurafeed.features.splash.pages.SplashPage
import ui.theme.AppThemeManager

@Composable
@Preview
fun App() {
    // ThemeManager থেকে বর্তমান থিম মোডটা কালেক্ট করা হচ্ছে
    val currentThemeMode by AppThemeManager.themeMode.collectAsState()
    AppTheme(themeMode = currentThemeMode) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Splash){
            composable<Splash> {
                SplashPage(navController)
            }

            composable<Shell> {
                ShellPage(navController)
            }
        }
    }
}