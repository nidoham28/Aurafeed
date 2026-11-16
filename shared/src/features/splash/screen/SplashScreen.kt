package io.aurafeed.app.features.splash.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
internal fun SplashScreen(onContinue: () -> Unit) {
    LaunchedEffect(Unit) {
        onContinue()
    }
}
