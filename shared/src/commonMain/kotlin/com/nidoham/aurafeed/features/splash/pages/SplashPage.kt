package com.nidoham.aurafeed.features.splash.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aurafeed.shared.generated.resources.Res
import aurafeed.shared.generated.resources.icons
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashPage(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
){
    LaunchedEffect(Unit){
        delay(2000.milliseconds)
        onNavigateToLogin()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(modifier = Modifier.size(150.dp),
            painter = painterResource(Res.drawable.icons),
            contentDescription = null
        )
    }
}