package io.aurafeed.app.ui.theme

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
 * AuraFeed effects
 * ----------------
 * Depth comes from three things only: soft blue-tinted shadows, translucent glass
 * surfaces with a hairline highlight, and one ambient "aurora" glow behind hero areas.
 * Everything else stays flat.
 */

// region Elevation

/** Elevation levels. Keep to these five so depth stays meaningful. */
enum class AuraElevation(val dp: Dp) {
    None(0.dp),
    /** Resting cards. */
    Raised(2.dp),
    /** Sticky bars, FAB at rest. */
    Floating(8.dp),
    /** Menus, popovers. */
    Overlay(16.dp),
    /** Dialogs, sheets. */
    Modal(28.dp),
}

/**
 * Soft shadow tinted by the theme ([AuraExtendedColors.shadowTint]): cool blue in light
 * mode, black in dark mode. Does not clip content; pair with `clip` or `background` as needed.
 */
fun Modifier.auraShadow(
    elevation: AuraElevation = AuraElevation.Raised,
    shape: Shape = AuraShapes.card,
): Modifier = composed {
    if (elevation == AuraElevation.None) {
        Modifier
    } else {
        val tint = AuraTheme.colors.shadowTint
        Modifier.shadow(
            elevation = elevation.dp,
            shape = shape,
            clip = false,
            ambientColor = tint.copy(alpha = 0.14f),
            spotColor = tint.copy(alpha = 0.28f),
        )
    }
}

/** 0.75dp outline using the theme's `outlineVariant`. Pair with flat cards in dark mode. */
fun Modifier.auraHairline(
    shape: Shape = AuraShapes.card,
    color: Color? = null,
): Modifier = composed {
    border(
        width = 0.75.dp,
        color = color ?: androidx.compose.material3.MaterialTheme.colorScheme.outlineVariant,
        shape = shape,
    )
}

// endregion

// region Surfaces

/**
 * Translucent glass surface: tinted fill plus a top-lit hairline stroke.
 *
 * Note: this is a translucent look, not a true backdrop blur (Compose Multiplatform has no
 * common backdrop-blur API). It reads best over imagery or [auroraBackdrop].
 */
fun Modifier.glass(
    shape: Shape = AuraShapes.card,
): Modifier = composed {
    val colors = AuraTheme.colors
    val stroke = colors.glassStroke
    clip(shape)
        .background(colors.glassFill)
        .border(
            width = 0.75.dp,
            brush = Brush.linearGradient(
                colors = listOf(stroke, stroke.copy(alpha = stroke.alpha * 0.2f)),
            ),
            shape = shape,
        )
}

/**
 * Ambient aurora wash for hero areas (profile header, onboarding, empty states).
 * Draw it on the *background* of a container; keep [intensity] low behind text.
 */
fun Modifier.auroraBackdrop(intensity: Float = 1f): Modifier = composed {
    val colors = AuraTheme.colors
    val base = if (colors.isDark) 0.22f else 0.26f
    val alpha = (base * intensity).coerceIn(0f, 1f)
    drawBehind {
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(colors.auroraCyan.copy(alpha = alpha), Color.Transparent),
                center = Offset(size.width * 0.12f, 0f),
                radius = size.width * 0.95f,
            ),
        )
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(colors.auroraViolet.copy(alpha = alpha * 0.8f), Color.Transparent),
                center = Offset(size.width * 0.95f, size.height * 0.08f),
                radius = size.width * 0.8f,
            ),
        )
    }
}

/**
 * Colored glow around an element (live badge, active story ring, primary FAB).
 * Drawn outside the element's bounds, so the parent must not clip it.
 */
fun Modifier.auraGlow(
    color: Color,
    spread: Dp = 24.dp,
    alpha: Float = 0.35f,
): Modifier = drawBehind {
    val pad = spread.toPx()
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(color.copy(alpha = alpha), Color.Transparent),
            center = center,
            radius = maxOf(size.width, size.height) / 2f + pad,
        ),
        topLeft = Offset(-pad, -pad),
        size = Size(size.width + pad * 2f, size.height + pad * 2f),
    )
}

// endregion

// region Motion

/** Motion tokens. Springs for movement and size, tweens for color and opacity. */
object AuraMotion {
    const val Fast = 140
    const val Medium = 240
    const val Slow = 420

    /** Fast out, slow settle. Use for entering elements. */
    val Emphasized = CubicBezierEasing(0.2f, 0f, 0f, 1f)

    /** Default spatial spring: gentle settle, no visible bounce. */
    fun <T> spatial(): AnimationSpec<T> =
        spring(dampingRatio = 0.85f, stiffness = Spring.StiffnessMediumLow)

    /** Snappy spring for direct-manipulation feedback (press, toggle). */
    fun <T> snappy(): AnimationSpec<T> =
        spring(dampingRatio = 0.72f, stiffness = Spring.StiffnessMedium)

    /** Non-spatial changes such as color and opacity. */
    fun <T> effects(): AnimationSpec<T> =
        tween(durationMillis = Medium, easing = Emphasized)
}

/** Scales the element down slightly while pressed. Feed the same [InteractionSource] used by the clickable. */
fun Modifier.pressScale(
    interactionSource: InteractionSource,
    pressedScale: Float = 0.97f,
): Modifier = composed {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) pressedScale else 1f,
        animationSpec = AuraMotion.snappy(),
        label = "pressScale",
    )
    graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}

/** Skeleton loading sweep for feed placeholders. */
fun Modifier.shimmer(
    shape: Shape = AuraShapes.tag,
    enabled: Boolean = true,
): Modifier = if (!enabled) {
    this
} else {
    composed {
        val scheme = androidx.compose.material3.MaterialTheme.colorScheme
        val base = scheme.surfaceContainerHigh
        val highlight = scheme.surfaceContainerHighest
        val transition = rememberInfiniteTransition(label = "shimmer")
        val progress by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(durationMillis = 1400, easing = LinearEasing)),
            label = "shimmerProgress",
        )
        clip(shape).drawBehind {
            val w = size.width
            val x = -w + progress * 3f * w
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(base, highlight, base),
                    start = Offset(x, 0f),
                    end = Offset(x + w, size.height * 0.3f),
                ),
            )
        }
    }
}

// endregion
