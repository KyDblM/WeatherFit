package com.example.weatherfit.presentation.util

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.weatherfit.domain.util.MannequinGender

val LocalGender = staticCompositionLocalOf {
    MannequinGender.MALE
}