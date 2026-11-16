package io.aurafeed.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

/*
 * AuraFeed typography
 * -------------------
 * Two families with distinct jobs:
 *   • Display  – Sora (SemiBold/Bold). Geometric and slightly technical; used for display,
 *                headline and large KPI numbers. Tracking is tightened as size grows.
 *   • Body     – Figtree (Regular/Medium/SemiBold). Friendly, highly legible at 12–16sp;
 *                used for posts, titles, labels and UI chrome.
 *
 * No all-caps labels: hierarchy comes from size and weight, not shouting.
 */

/**
 * Font families used by the theme. Defaults to the platform font so the project compiles
 * before any font assets exist.
 *
 * To wire real fonts (Compose Multiplatform resources), drop static TTFs into
 * `composeResources/font/` (sora_semibold.ttf, sora_bold.ttf, figtree_regular.ttf,
 * figtree_medium.ttf, figtree_semibold.ttf) and build the families where `Res` is available:
 *
 * ```kotlin
 * @Composable
 * fun rememberAuraFonts(): AuraFonts {
 *     val sora = FontFamily(
 *         Font(Res.font.sora_semibold, FontWeight.SemiBold),
 *         Font(Res.font.sora_bold, FontWeight.Bold),
 *     )
 *     val figtree = FontFamily(
 *         Font(Res.font.figtree_regular, FontWeight.Normal),
 *         Font(Res.font.figtree_medium, FontWeight.Medium),
 *         Font(Res.font.figtree_semibold, FontWeight.SemiBold),
 *     )
 *     return remember(sora, figtree) { AuraFonts(display = sora, body = figtree) }
 * }
 *
 * AuraFeedTheme(fonts = rememberAuraFonts()) { App() }
 * ```
 */
@Immutable
class AuraFonts(
    val display: FontFamily,
    val body: FontFamily,
) {
    companion object {
        val System = AuraFonts(display = FontFamily.Default, body = FontFamily.Default)
    }
}

/** Centers glyphs inside the line box so text sits identically on Android, iOS and Desktop. */
private val BalancedLines = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None,
)

private fun aura(
    family: FontFamily,
    weight: FontWeight,
    size: Int,
    line: Int,
    tracking: Double,
    features: String? = null,
) = TextStyle(
    fontFamily = family,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = line.sp,
    letterSpacing = tracking.sp,
    fontFeatureSettings = features,
    lineHeightStyle = BalancedLines,
)

/** Material 3 type scale tuned for AuraFeed. */
fun auraTypography(fonts: AuraFonts = AuraFonts.System): Typography {
    val d = fonts.display
    val b = fonts.body
    return Typography(
        displayLarge = aura(d, FontWeight.SemiBold, 52, 58, -1.2),
        displayMedium = aura(d, FontWeight.SemiBold, 42, 48, -0.9),
        displaySmall = aura(d, FontWeight.SemiBold, 34, 40, -0.6),

        headlineLarge = aura(d, FontWeight.SemiBold, 30, 36, -0.5),
        headlineMedium = aura(d, FontWeight.SemiBold, 26, 32, -0.4),
        headlineSmall = aura(d, FontWeight.SemiBold, 22, 28, -0.25),

        titleLarge = aura(d, FontWeight.SemiBold, 20, 26, -0.15),
        titleMedium = aura(b, FontWeight.SemiBold, 16, 22, 0.1),
        titleSmall = aura(b, FontWeight.SemiBold, 14, 20, 0.1),

        bodyLarge = aura(b, FontWeight.Normal, 16, 24, 0.1),
        bodyMedium = aura(b, FontWeight.Normal, 14, 20, 0.15),
        bodySmall = aura(b, FontWeight.Normal, 12, 16, 0.2),

        labelLarge = aura(b, FontWeight.SemiBold, 14, 20, 0.1),
        labelMedium = aura(b, FontWeight.Medium, 12, 16, 0.3),
        labelSmall = aura(b, FontWeight.Medium, 11, 16, 0.4),
    )
}

/**
 * Product-specific styles that don't map cleanly onto the Material scale.
 * Colors are intentionally left unset so callers pick them from the color scheme.
 *
 * Access through [AuraTheme.text].
 */
@Immutable
class AuraTextStyles(
    /** Standard post copy. 16/25 keeps long threads comfortable to read. */
    val postBody: TextStyle,
    /** Long-form articles, newsletters, expanded posts. */
    val postBodyLarge: TextStyle,
    /** Author display name in a post header. */
    val author: TextStyle,
    /** @handle, secondary identity line. */
    val handle: TextStyle,
    /** Relative timestamps ("4m", "Yesterday"). */
    val timestamp: TextStyle,
    /** #hashtags and @mentions inside post copy. */
    val link: TextStyle,
    /** Like / comment / share counters. Tabular figures so numbers don't jitter. */
    val metric: TextStyle,
    /** Large KPI value on analytics cards. */
    val kpi: TextStyle,
    /** Compact KPI value for dense dashboards. */
    val kpiCompact: TextStyle,
)

fun auraTextStyles(fonts: AuraFonts = AuraFonts.System): AuraTextStyles {
    val d = fonts.display
    val b = fonts.body
    return AuraTextStyles(
        postBody = aura(b, FontWeight.Normal, 16, 25, 0.1),
        postBodyLarge = aura(b, FontWeight.Normal, 18, 29, 0.0),
        author = aura(b, FontWeight.SemiBold, 15, 20, 0.0),
        handle = aura(b, FontWeight.Medium, 14, 20, 0.1),
        timestamp = aura(b, FontWeight.Normal, 12, 16, 0.2),
        link = aura(b, FontWeight.SemiBold, 16, 25, 0.1),
        metric = aura(b, FontWeight.Medium, 13, 16, 0.2, features = "tnum"),
        kpi = aura(d, FontWeight.SemiBold, 34, 40, -0.7, features = "tnum"),
        kpiCompact = aura(d, FontWeight.SemiBold, 24, 28, -0.4, features = "tnum"),
    )
}