package com.example.weatherfit.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherfit.R

private val Comfortaa = FontFamily(
    Font(R.font.comfortaa_medium, FontWeight.Medium),
)

private val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
)