package com.nidoham.aurafeed.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.theme.ThemeMode

// --- আগের মতো কালার স্কিমগুলো (Color Schemes) ---
private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,

    secondary = Secondary80,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,

    tertiary = Tertiary80,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,

    background = DarkSurface,
    onBackground = DarkOnSurface,

    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceContainer,
    onSurfaceVariant = DarkOnSurfaceVariant,

    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    scrim = DarkScrim
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Primary80,
    onPrimaryContainer = Primary10,

    secondary = Secondary40,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Secondary80,
    onSecondaryContainer = Secondary20,

    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F)

    // লাইট থিমের বাকি রংগুলো তুমি চাইলে Color.kt থেকে যোগ করে নিতে পারো
)

// --- মূল থিম ফাংশন ---
@Composable
fun AppTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM, // থিম ম্যানেজার থেকে মোড আসবে
    content: @Composable () -> Unit
) {
    // ডিভাইস ডার্ক মোডে আছে কি না তা চেক করা
    val systemInDark = isSystemInDarkTheme()

    // থিম ম্যানেজারের মোড অনুযায়ী ডার্ক নাকি লাইট তা ঠিক করা
    val isDarkTheme = when (themeMode) {
        ThemeMode.SYSTEM -> systemInDark
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }
    // ডিভাইস ডার্ক মোডে আছে কি না তার ওপর ভিত্তি করে কালার স্কিম বাছাই করা
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    // MaterialTheme-এর ভেতর কালার এবং টাইপোগ্রাফি পাস করা
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography, // এটা Type.kt ফাইল থেকে এসেছে
        content = content
    )
}