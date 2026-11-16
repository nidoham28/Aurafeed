package io.aurafeed.app.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/*
 * AuraFeed color system
 * ---------------------
 * Direction: cold + bright. Cobalt is the brand voice, glacier cyan is the supporting
 * accent, frost violet is reserved for highlights. Neutrals are tinted blue (never pure
 * grey) so surfaces feel like one family. Light mode stays bright with white cards on a
 * cool-white canvas; dark mode uses deep navy instead of neutral black.
 *
 * Contrast: every on* pair meets WCAG AA (4.5:1) for text. Gradients that carry text use
 * `brand`; the purely decorative `aurora` gradient must never sit behind body text.
 */

// region Light scheme

internal val AuraLightColorScheme: ColorScheme = lightColorScheme(
    primary = Color(0xFF1A63F0),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDCE8FF),
    onPrimaryContainer = Color(0xFF00226A),
    inversePrimary = Color(0xFF9DBBFF),

    secondary = Color(0xFF007C96),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFCFF3FB),
    onSecondaryContainer = Color(0xFF00323D),

    tertiary = Color(0xFF5B5FEF),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE4E5FF),
    onTertiaryContainer = Color(0xFF15176A),

    error = Color(0xFFD92D4B),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFE0E5),
    onErrorContainer = Color(0xFF5A0016),

    background = Color(0xFFF6F9FE),
    onBackground = Color(0xFF0E1B2E),
    surface = Color(0xFFF6F9FE),
    onSurface = Color(0xFF0E1B2E),
    surfaceVariant = Color(0xFFE3ECF7),
    onSurfaceVariant = Color(0xFF46576E),
    surfaceTint = Color(0xFF1A63F0),
    inverseSurface = Color(0xFF14243B),
    inverseOnSurface = Color(0xFFEAF1FB),

    outline = Color(0xFF7D8EA6),
    outlineVariant = Color(0xFFD0DCEA),
    scrim = Color(0xFF000B1F),

    surfaceDim = Color(0xFFD5DFEB),
    surfaceBright = Color(0xFFFFFFFF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF0F5FB),
    surfaceContainer = Color(0xFFEAF1F9),
    surfaceContainerHigh = Color(0xFFE3ECF6),
    surfaceContainerHighest = Color(0xFFDCE6F2),
)

// endregion

// region Dark scheme

internal val AuraDarkColorScheme: ColorScheme = darkColorScheme(
    primary = Color(0xFF8CB0FF),
    onPrimary = Color(0xFF00267A),
    primaryContainer = Color(0xFF1A4FCC),
    onPrimaryContainer = Color(0xFFDCE8FF),
    inversePrimary = Color(0xFF1A63F0),

    secondary = Color(0xFF5FDDF2),
    onSecondary = Color(0xFF00333D),
    secondaryContainer = Color(0xFF004D5C),
    onSecondaryContainer = Color(0xFFBDF2FF),

    tertiary = Color(0xFFB3B7FF),
    onTertiary = Color(0xFF1B1D82),
    tertiaryContainer = Color(0xFF3A3EC4),
    onTertiaryContainer = Color(0xFFE4E5FF),

    error = Color(0xFFFF8FA1),
    onError = Color(0xFF64001A),
    errorContainer = Color(0xFF8C1030),
    onErrorContainer = Color(0xFFFFDAE0),

    background = Color(0xFF081321),
    onBackground = Color(0xFFE4ECF8),
    surface = Color(0xFF081321),
    onSurface = Color(0xFFE4ECF8),
    surfaceVariant = Color(0xFF1E3049),
    onSurfaceVariant = Color(0xFFA8B8CF),
    surfaceTint = Color(0xFF8CB0FF),
    inverseSurface = Color(0xFFE4ECF8),
    inverseOnSurface = Color(0xFF14243B),

    outline = Color(0xFF6C7F99),
    outlineVariant = Color(0xFF25384F),
    scrim = Color(0xFF000000),

    surfaceDim = Color(0xFF060F1B),
    surfaceBright = Color(0xFF24385A),
    surfaceContainerLowest = Color(0xFF050D18),
    surfaceContainerLow = Color(0xFF0C192B),
    surfaceContainer = Color(0xFF111F34),
    surfaceContainerHigh = Color(0xFF18283F),
    surfaceContainerHighest = Color(0xFF1F3350),
)

// endregion

// region Extended (non-Material) colors

/**
 * Semantic colors Material 3 does not provide: status colors, social-specific accents,
 * brand gradients, glass surfaces and chart series for analytics screens.
 *
 * Access through [AuraTheme.colors].
 */
@Immutable
class AuraExtendedColors(
    val isDark: Boolean,

    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,

    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,

    /** Verified-account badge. */
    val verified: Color,
    /** Live / recording indicator. */
    val live: Color,
    /** Like / favorite state. */
    val like: Color,

    /** Text-safe brand gradient for filled buttons and hero surfaces (white text passes AA). */
    val brand: Brush,
    /** Decorative gradient (cobalt → cyan → violet) for rings, strokes and accents. No text on top. */
    val aurora: Brush,

    val auroraBlue: Color,
    val auroraCyan: Color,
    val auroraViolet: Color,

    /** Fill for translucent "glass" surfaces. */
    val glassFill: Color,
    /** Hairline stroke for glass surfaces. */
    val glassStroke: Color,
    /** Tint used for elevation shadows (cool blue in light mode, black in dark mode). */
    val shadowTint: Color,

    /** Six-series categorical palette for charts and analytics. */
    val chart: List<Color>,
)

private val BrandCobalt = Color(0xFF1A63F0)
private val BrandCyanDeep = Color(0xFF0C7FD0)

internal val AuraLightExtendedColors = AuraExtendedColors(
    isDark = false,

    success = Color(0xFF08855B),
    onSuccess = Color(0xFFFFFFFF),
    successContainer = Color(0xFFD3F6E6),
    onSuccessContainer = Color(0xFF00382A),

    warning = Color(0xFFA35F00),
    onWarning = Color(0xFFFFFFFF),
    warningContainer = Color(0xFFFFEFCF),
    onWarningContainer = Color(0xFF4A2B00),

    verified = Color(0xFF0098D6),
    live = Color(0xFFE5254A),
    like = Color(0xFFFF3D71),

    brand = Brush.linearGradient(listOf(BrandCobalt, BrandCyanDeep)),
    aurora = Brush.linearGradient(listOf(Color(0xFF1A63F0), Color(0xFF12B8E8), Color(0xFF7C6CF0))),

    auroraBlue = Color(0xFF1A63F0),
    auroraCyan = Color(0xFF12B8E8),
    auroraViolet = Color(0xFF7C6CF0),

    glassFill = Color(0xFFFFFFFF).copy(alpha = 0.72f),
    glassStroke = Color(0xFFFFFFFF).copy(alpha = 0.90f),
    shadowTint = Color(0xFF1A3A8F),

    chart = listOf(
        Color(0xFF1A63F0), // cobalt
        Color(0xFF00B8D9), // cyan
        Color(0xFF7C6CF0), // violet
        Color(0xFF12B886), // mint
        Color(0xFFFFB020), // amber
        Color(0xFFFF5C8A), // coral
    ),
)

internal val AuraDarkExtendedColors = AuraExtendedColors(
    isDark = true,

    success = Color(0xFF5AE0A8),
    onSuccess = Color(0xFF003824),
    successContainer = Color(0xFF0B4D36),
    onSuccessContainer = Color(0xFFB6F5DA),

    warning = Color(0xFFFFC266),
    onWarning = Color(0xFF4A2B00),
    warningContainer = Color(0xFF6B4200),
    onWarningContainer = Color(0xFFFFE2B0),

    verified = Color(0xFF4FC3F7),
    live = Color(0xFFFF5C7A),
    like = Color(0xFFFF6B8B),

    brand = Brush.linearGradient(listOf(Color(0xFF2F6BFF), Color(0xFF0C7FD0))),
    aurora = Brush.linearGradient(listOf(Color(0xFF5B8CFF), Color(0xFF35D0F0), Color(0xFF9B8CFF))),

    auroraBlue = Color(0xFF3F7BFF),
    auroraCyan = Color(0xFF35D0F0),
    auroraViolet = Color(0xFF9B8CFF),

    glassFill = Color(0xFFFFFFFF).copy(alpha = 0.06f),
    glassStroke = Color(0xFFFFFFFF).copy(alpha = 0.14f),
    shadowTint = Color(0xFF000000),

    chart = listOf(
        Color(0xFF8CB0FF),
        Color(0xFF5FE0F5),
        Color(0xFFB3B7FF),
        Color(0xFF5AE0A8),
        Color(0xFFFFC266),
        Color(0xFFFF8FA1),
    ),
)

// endregion