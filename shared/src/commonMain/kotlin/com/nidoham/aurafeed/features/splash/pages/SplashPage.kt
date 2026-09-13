package com.nidoham.aurafeed.features.splash.pages

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import aurafeed.shared.generated.resources.Res
import aurafeed.shared.generated.resources.icons
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.milliseconds

/** How long the splash logo stays visible before navigating away. */
private val SPLASH_DURATION = 2000.milliseconds

/** Duration of the logo's fade + scale entrance animation. */
private const val LOGO_ANIM_DURATION_MS = 500

@Composable
fun SplashPage(
    isLoggedIn: suspend () -> Boolean = { false },
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    var isVisible by remember { mutableStateOf(false) }

    val logoScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = tween(durationMillis = LOGO_ANIM_DURATION_MS, easing = EaseOutCubic),
        label = "logoScale",
    )
    val logoAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = LOGO_ANIM_DURATION_MS, easing = EaseOutCubic),
        label = "logoAlpha",
    )

    LaunchedEffect(Unit) {
        isVisible = true
        delay(SPLASH_DURATION)
        if (isLoggedIn()) onNavigateToHome() else onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .size(150.dp)
                .scale(logoScale)
                .alpha(logoAlpha),
            painter = painterResource(Res.drawable.icons),
            contentDescription = null,
        )
    }
}