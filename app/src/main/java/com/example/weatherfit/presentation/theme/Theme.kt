package com.example.weatherfit.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = Blue500,
    secondary = Blue200,
    tertiary = Green500,
    background = Blue50,
    surface = White50,
    onPrimary = White50,
    onSecondary = White50,
    onTertiary = White50,
    onBackground = Black900,
    onSurface = Black900,
    surfaceDim = Gray100
)

val DarkColorScheme = darkColorScheme(
    primary = Blue500,
    secondary = Blue200,
    tertiary = Green500,
    background = Gray900,
    surface = Gray700,
    onPrimary = White50,
    onSecondary = Black900,
    onTertiary = Black900,
    onBackground = Black900,
    onSurface = White50,
    surfaceDim = Gray500
)

@Composable
fun WeatherFitTheme(
    isDarkTheme: Boolean?,
    content: @Composable () -> Unit
) {
    val darkTheme = isDarkTheme ?: isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}