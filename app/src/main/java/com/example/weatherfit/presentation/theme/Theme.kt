package com.example.weatherfit.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
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
)

private val LightColorScheme = lightColorScheme(
    primary = Blue500,
    secondary = Blue200,
    tertiary = Green500,
    background = Gray900,
    surface = Gray700,
    onPrimary = Black900,
    onSecondary = Black900,
    onTertiary = Black900,
    onBackground = Black900,
    onSurface = White50,
)

@Composable
fun WeatherFitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}