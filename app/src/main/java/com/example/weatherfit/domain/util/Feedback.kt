package com.example.weatherfit.domain.util

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Feedback(
    @DrawableRes val image: Int,
    val effectsOnColdSensitivity: Float
)
