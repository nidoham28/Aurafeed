package com.nidoham.aurafeed.features.auth.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.nidoham.aurafeed.features.auth.domain.AuthMode

@Composable
fun AuthScreen(navController: NavController){
    var currentScreen by remember { mutableStateOf(AuthMode.LOGIN) }

    when (currentScreen) {
        AuthMode.LOGIN -> LoginScreen(onLoginClick = { email, password ->
            // Handle login logic here
        }, onRegisterClick = {
            currentScreen = AuthMode.REGISTER
        })

        AuthMode.REGISTER -> RegisterScreen(onRegisterClick = { username, email, password, dob, gender ->
            // Handle register logic here
        }, onLoginClick = {
            currentScreen = AuthMode.LOGIN
        })
    }
}