package com.example.wishlistapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = primaryColor_dark,
    onPrimary = onPrimaryColor_dark,
    secondary = secondaryColor_dark,
    onSecondary = onSecondaryColor_dark,
    tertiary = tertiaryColor_dark,
    onTertiary = onTertiaryColor_dark,
    background = backgroundColor_dark,
    onBackground = onBackgroundColor_dark,
    surface = surfaceColor_dark,
    onSurface = onSurfaceColor_dark,
    surfaceVariant = surfaceVariantColor_dark,
    onSurfaceVariant = onSurfaceVariantColor_dark,
    error = errorColor_dark,
    onError = onErrorColor_dark,
    outline = disabledBorderColor_dark,
    outlineVariant = disabledBorderColor_dark,
    inverseOnSurface = surfaceColor_dark,
    inverseSurface = surfaceColor_dark,
    inversePrimary = primaryColor_dark,
    surfaceTint = primaryColor_dark,
    scrim = surfaceColor_dark,
)


private val LightColorScheme = lightColorScheme(
    primary = primaryColor_light,
    onPrimary = onPrimaryColor_light,
    secondary = secondaryColor_light,
    onSecondary = onSecondaryColor_light,
    tertiary = tertiaryColor_light,
    onTertiary = onTertiaryColor_light,
    background = backgroundColor_light,
    onBackground = onBackgroundColor_light,
    surface = surfaceColor_light,
    onSurface = onSurfaceColor_light,
    surfaceVariant = surfaceVariantColor_light,
    onSurfaceVariant = onSurfaceVariantColor_light,
    error = errorColor_light,
    onError = onErrorColor_light,
    outlineVariant = surfaceVariantColor_light,
    outline = surfaceVariantColor_light,
    inverseOnSurface = surfaceColor_light,
    inverseSurface = surfaceColor_light,
    inversePrimary = primaryColor_light,
    surfaceTint = primaryColor_light,
    scrim = surfaceColor_light,
)

private val WarmColorScheme = lightColorScheme(
    primary = primaryColor_warm,
    onPrimary = onPrimaryColor_warm,
    secondary = secondaryColor_warm,
    onSecondary = onSecondaryColor_warm,
    tertiary = tertiaryColor_warm,
    onTertiary = onTertiaryColor_warm,
    background = backgroundColor_warm,
    onBackground = onBackgroundColor_warm,
    surface = surfaceColor_warm,
    onSurface = onSurfaceColor_warm,
    surfaceVariant = surfaceVariantColor_warm,
    onSurfaceVariant = onSurfaceVariantColor_warm,
    error = errorColor_warm,
    onError = onErrorColor_warm,
    outlineVariant = surfaceVariantColor_warm,
    outline = surfaceVariantColor_warm,
    inverseOnSurface = surfaceColor_warm,
    inverseSurface = surfaceColor_warm,
    inversePrimary = primaryColor_warm,
    surfaceTint = primaryColor_warm,
    scrim = surfaceColor_warm,
)


@Composable
fun WishlistApplicationTheme(
    darkTheme: Boolean,
    warmTheme: Boolean,
    lightTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        lightTheme -> LightColorScheme
        warmTheme -> WarmColorScheme
        darkTheme -> DarkColorScheme
        else -> if(isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}