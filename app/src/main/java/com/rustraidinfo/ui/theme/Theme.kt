package com.rustraidinfo.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val RustColorScheme = darkColorScheme(
    primary = RustRed,
    onPrimary = Color.White,
    primaryContainer = RustDarkRed,
    onPrimaryContainer = Color.White,
    secondary = RustOrange,
    onSecondary = Color.Black,
    secondaryContainer = RustCardBg,
    onSecondaryContainer = RustTextPrimary,
    tertiary = RustGold,
    onTertiary = Color.Black,
    tertiaryContainer = RustSurface,
    onTertiaryContainer = RustTextPrimary,
    error = RustAccentRed,
    onError = Color.White,
    errorContainer = RustDarkRed,
    onErrorContainer = Color.White,
    background = RustDarker,
    onBackground = RustTextPrimary,
    surface = RustDark,
    onSurface = RustTextPrimary,
    surfaceVariant = RustCardBg,
    onSurfaceVariant = RustTextSecondary,
    outline = RustTextMuted,
    outlineVariant = RustCardBg,
    inverseSurface = Color.White,
    inverseOnSurface = RustDark,
    inversePrimary = RustRed,
    surfaceTint = RustRed
)

@Composable
fun RustRaidInfoTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = RustColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = RustDarker.toArgb()
            window.navigationBarColor = RustDarker.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = RustTypography,
        content = content
    )
}

