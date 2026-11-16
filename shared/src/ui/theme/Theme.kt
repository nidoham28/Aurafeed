package io.aurafeed.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** User-facing theme preference (store this in settings). */
enum class ThemeMode { System, Light, Dark }

@Composable
fun ThemeMode.isDark(): Boolean = when (this) {
    ThemeMode.System -> isSystemInDarkTheme()
    ThemeMode.Light -> false
    ThemeMode.Dark -> true
}

/** 4dp-based spacing scale plus feed layout constants. */
@Immutable
data class AuraSpacing(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 24.dp,
    val xxl: Dp = 32.dp,
    val xxxl: Dp = 48.dp,
    /** Horizontal screen padding on compact widths. */
    val gutter: Dp = 16.dp,
    /** Max width of the main feed column on tablet, desktop and web. */
    val feedMaxWidth: Dp = 640.dp,
    /** Width of the navigation rail on medium and large windows. */
    val railWidth: Dp = 80.dp,
)

private val LocalAuraColors = staticCompositionLocalOf { AuraLightExtendedColors }
private val LocalAuraSpacing = staticCompositionLocalOf { AuraSpacing() }
private val LocalAuraTextStyles = staticCompositionLocalOf { auraTextStyles() }

/**
 * Root theme for AuraFeed. Wrap the app once, at the top of the shared UI:
 *
 * ```kotlin
 * AuraFeedTheme(darkTheme = themeMode.isDark(), fonts = rememberAuraFonts()) { App() }
 * ```
 *
 * Dynamic (wallpaper) color is intentionally not used: the cold-cobalt palette is the
 * brand, and it must look identical on Android, iOS, Desktop and Web.
 */
@Composable
fun AurafeedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    fonts: AuraFonts = AuraFonts.System,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) AuraDarkColorScheme else AuraLightColorScheme
    val extendedColors = if (darkTheme) AuraDarkExtendedColors else AuraLightExtendedColors
    val typography = remember(fonts) { auraTypography(fonts) }
    val textStyles = remember(fonts) { auraTextStyles(fonts) }

    CompositionLocalProvider(
        LocalAuraColors provides extendedColors,
        LocalAuraSpacing provides AuraSpacing(),
        LocalAuraTextStyles provides textStyles,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = AuraMaterialShapes,
            content = content,
        )
    }
}

/**
 * Accessor for AuraFeed tokens that sit beside `MaterialTheme`:
 * `AuraTheme.colors.success`, `AuraTheme.text.metric`, `AuraTheme.shapes.avatar`, `AuraTheme.spacing.lg`.
 */
object AuraTheme {
    val colors: AuraExtendedColors
        @Composable @ReadOnlyComposable get() = LocalAuraColors.current

    val text: AuraTextStyles
        @Composable @ReadOnlyComposable get() = LocalAuraTextStyles.current

    val spacing: AuraSpacing
        @Composable @ReadOnlyComposable get() = LocalAuraSpacing.current

    val shapes: AuraShapes
        get() = AuraShapes
}