package io.aurafeed.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sin

/*
 * AuraFeed shapes
 * ---------------
 * Radius scales with the size of the element so large surfaces feel soft and small
 * controls stay crisp. Identity elements (avatars, story rings, app tiles) use a true
 * superellipse "squircle" instead of a circle, which is the signature silhouette.
 */

/** Material 3 shape slots. Used by buttons, cards, dialogs, menus, text fields. */
internal val AuraMaterialShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(30.dp),
)

/**
 * A superellipse (|x/a|^n + |y/b|^n = 1), the smooth "squircle" curve.
 *
 * Best on square or near-square elements (avatars, icon tiles, FABs). On wide
 * rectangles prefer [RoundedCornerShape], because a superellipse stretches with the box.
 *
 * @param exponent 2 = ellipse, 4–5 = squircle, higher = closer to a rounded square.
 */
@Immutable
class SuperellipseShape(
    private val exponent: Float = 4.5f,
    private val steps: Int = 96,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val a = size.width / 2.0
        val b = size.height / 2.0
        val power = 2.0 / exponent
        val path = Path()
        for (i in 0..steps) {
            val t = 2.0 * PI * i / steps
            val c = cos(t)
            val s = sin(t)
            val x = (a + a * sign(c) * abs(c).pow(power)).toFloat()
            val y = (b + b * sign(s) * abs(s).pow(power)).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        return Outline.Generic(path)
    }

    override fun equals(other: Any?): Boolean =
        other is SuperellipseShape && other.exponent == exponent && other.steps == steps

    override fun hashCode(): Int = 31 * exponent.hashCode() + steps
}

/** Named shapes for product surfaces. Access through [AuraTheme.shapes]. */
object AuraShapes {
    /** Pills: chips, tags, segmented controls, search bar, primary buttons. */
    val pill: Shape = RoundedCornerShape(percent = 50)

    /** Small tags and inline badges. */
    val tag: Shape = RoundedCornerShape(10.dp)

    /** Text fields and compact inputs. */
    val input: Shape = RoundedCornerShape(16.dp)

    /** Post cards, analytics cards, list containers. */
    val card: Shape = RoundedCornerShape(22.dp)

    /** Images and video inside a post. Slightly tighter than the card so the media nests inside it. */
    val media: Shape = RoundedCornerShape(16.dp)

    /** Bottom sheets and expanded composer. */
    val sheet: Shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)

    /** Dialogs and popovers. */
    val dialog: Shape = RoundedCornerShape(28.dp)

    /** Avatars, story rings, app tiles. */
    val avatar: Shape = SuperellipseShape(exponent = 4.5f)

    /** Incoming chat bubble: tail corner at the bottom start. */
    val bubbleIncoming: Shape = RoundedCornerShape(
        topStart = 20.dp, topEnd = 20.dp, bottomEnd = 20.dp, bottomStart = 6.dp,
    )

    /** Outgoing chat bubble: tail corner at the bottom end. */
    val bubbleOutgoing: Shape = RoundedCornerShape(
        topStart = 20.dp, topEnd = 20.dp, bottomEnd = 6.dp, bottomStart = 20.dp,
    )
}
