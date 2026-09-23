package com.nidoham.aurafeed.features.splash.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import aurafeed.shared.generated.resources.Res
import aurafeed.shared.generated.resources.app_icon
import com.nidoham.aurafeed.core.navigation.Auth
import com.nidoham.aurafeed.core.navigation.Shell
import com.nidoham.aurafeed.core.navigation.Splash
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.milliseconds

private const val SPLASH_DELAY = 3000L

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Image(
            painter = painterResource(Res.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
                .align(Alignment.Center)
        )
    }

    LaunchedEffect(Unit) {
        delay(SPLASH_DELAY.milliseconds)
        navController.navigate(Auth){
            popUpTo(Splash){
                inclusive = true
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashPagePreview() {
    val navController: NavHostController = rememberNavController()
    SplashScreen(navController = navController)
}