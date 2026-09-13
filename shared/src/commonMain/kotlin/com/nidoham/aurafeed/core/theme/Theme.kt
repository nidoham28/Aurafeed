package com.nidoham.aurafeed.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = AppColors.md_theme_light_primary,
    onPrimary = AppColors.md_theme_light_onPrimary,
    primaryContainer = AppColors.md_theme_light_primaryContainer,
    onPrimaryContainer = AppColors.md_theme_light_onPrimaryContainer,
    secondary = AppColors.md_theme_light_secondary,
    onSecondary = AppColors.md_theme_light_onSecondary,
    secondaryContainer = AppColors.md_theme_light_secondaryContainer,
    onSecondaryContainer = AppColors.md_theme_light_onSecondaryContainer,
    tertiary = AppColors.md_theme_light_tertiary,
    onTertiary = AppColors.md_theme_light_onTertiary,
    tertiaryContainer = AppColors.md_theme_light_tertiaryContainer,
    onTertiaryContainer = AppColors.md_theme_light_onTertiaryContainer,
    error = AppColors.md_theme_light_error,
    onError = AppColors.md_theme_light_onError,
    errorContainer = AppColors.md_theme_light_errorContainer,
    onErrorContainer = AppColors.md_theme_light_onErrorContainer,
    background = AppColors.md_theme_light_background,
    onBackground = AppColors.md_theme_light_onBackground,
    surface = AppColors.md_theme_light_surface,
    onSurface = AppColors.md_theme_light_onSurface,
    surfaceVariant = AppColors.md_theme_light_surfaceVariant,
    onSurfaceVariant = AppColors.md_theme_light_onSurfaceVariant,
    outline = AppColors.md_theme_light_outline,
)

private val DarkColors = darkColorScheme(
    primary = AppColors.md_theme_dark_primary,
    onPrimary = AppColors.md_theme_dark_onPrimary,
    primaryContainer = AppColors.md_theme_dark_primaryContainer,
    onPrimaryContainer = AppColors.md_theme_dark_onPrimaryContainer,
    secondary = AppColors.md_theme_dark_secondary,
    onSecondary = AppColors.md_theme_dark_onSecondary,
    secondaryContainer = AppColors.md_theme_dark_secondaryContainer,
    onSecondaryContainer = AppColors.md_theme_dark_onSecondaryContainer,
    tertiary = AppColors.md_theme_dark_tertiary,
    onTertiary = AppColors.md_theme_dark_onTertiary,
    tertiaryContainer = AppColors.md_theme_dark_tertiaryContainer,
    onTertiaryContainer = AppColors.md_theme_dark_onTertiaryContainer,
    error = AppColors.md_theme_dark_error,
    onError = AppColors.md_theme_dark_onError,
    errorContainer = AppColors.md_theme_dark_errorContainer,
    onErrorContainer = AppColors.md_theme_dark_onErrorContainer,
    background = AppColors.md_theme_dark_background,
    onBackground = AppColors.md_theme_dark_onBackground,
    surface = AppColors.md_theme_dark_surface,
    onSurface = AppColors.md_theme_dark_onSurface,
    surfaceVariant = AppColors.md_theme_dark_surfaceVariant,
    onSurfaceVariant = AppColors.md_theme_dark_onSurfaceVariant,
    outline = AppColors.md_theme_dark_outline,
    surfaceContainerLowest = AppColors.md_theme_dark_background,
    surfaceContainerLow = AppColors.md_theme_dark_surfaceContainerLow,
    surfaceContainer = AppColors.md_theme_dark_surfaceContainer,
    surfaceContainerHigh = AppColors.md_theme_dark_surfaceContainerHigh,
    surfaceContainerHighest = AppColors.md_theme_dark_surfaceContainerHighest,
)

/** Extra social-media/chat-only colors, exposed via CompositionLocal so they
 *  auto-swap with light/dark just like MaterialTheme.colorScheme does. */
data class ExtendedColors(
    val like: Color,
    val likeFilled: Color,
    val verifiedBadge: Color,
    val online: Color,
    val offline: Color,
    val newBadge: Color,
    val storyGradient: List<Color>,
    val storySeenRing: Color,
    val bubbleOutgoing: Color,
    val bubbleOutgoingText: Color,
    val bubbleIncoming: Color,
    val bubbleIncomingText: Color,
    val shimmerBase: Color,
    val shimmerHighlight: Color,
)

private val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        like = AppColors.like,
        likeFilled = AppColors.likeFilled,
        verifiedBadge = AppColors.verifiedBadge,
        online = AppColors.online,
        offline = AppColors.offline,
        newBadge = AppColors.newBadge,
        storyGradient = AppColors.storyGradient,
        storySeenRing = AppColors.storySeenRing,
        bubbleOutgoing = AppColors.bubbleOutgoing,
        bubbleOutgoingText = AppColors.bubbleOutgoingText,
        bubbleIncoming = AppColors.bubbleIncoming,
        bubbleIncomingText = AppColors.bubbleIncomingText,
        shimmerBase = AppColors.shimmerBaseLight,
        shimmerHighlight = AppColors.shimmerHighlightLight,
    )
}

/** Access via `AurafeedTheme.extendedColors.bubbleOutgoing`, `.like`, etc. */
object AurafeedExtendTheme {
    val extendedColors: ExtendedColors
        @Composable get() = LocalExtendedColors.current
}

@Composable
fun AurafeedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val extendedColors = ExtendedColors(
        like = AppColors.like,
        likeFilled = AppColors.likeFilled,
        verifiedBadge = AppColors.verifiedBadge,
        online = AppColors.online,
        offline = AppColors.offline,
        newBadge = AppColors.newBadge,
        storyGradient = AppColors.storyGradient,
        storySeenRing = AppColors.storySeenRing,
        bubbleOutgoing = AppColors.bubbleOutgoing,
        bubbleOutgoingText = AppColors.bubbleOutgoingText,
        bubbleIncoming = if (darkTheme) AppColors.bubbleIncoming else AppColors.md_theme_light_surfaceVariant,
        bubbleIncomingText = if (darkTheme) AppColors.bubbleIncomingText else AppColors.md_theme_light_onSurfaceVariant,
        shimmerBase = if (darkTheme) AppColors.shimmerBaseDark else AppColors.shimmerBaseLight,
        shimmerHighlight = if (darkTheme) AppColors.shimmerHighlightDark else AppColors.shimmerHighlightLight,
    )

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content,
        )
    }
}