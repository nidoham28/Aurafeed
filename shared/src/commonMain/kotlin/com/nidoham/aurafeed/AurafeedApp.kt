package com.nidoham.aurafeed

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nidoham.aurafeed.core.navigation.Shell
import com.nidoham.aurafeed.core.navigation.Splash
import com.nidoham.aurafeed.features.shell.pages.ShellPage
import com.nidoham.aurafeed.features.splash.pages.SplashPage

@Composable
fun AurafeedApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Splash
    ){
        composable<Splash>{
            SplashPage(
                onNavigateToLogin = {
                    navController.navigate(Shell){
                        popUpTo(Splash){
                            inclusive = true
                        }
                    }},
                onNavigateToHome = {
                    navController.navigate(Shell){
                        popUpTo(Splash){
                            inclusive = true
                        }
                    }}
            )
        }

        composable<Shell>{
            ShellPage()
        }
    }
}