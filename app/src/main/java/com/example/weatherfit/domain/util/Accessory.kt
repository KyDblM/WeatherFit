package com.example.weatherfit.domain.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Accessory(
    @DrawableRes val image: Int,
    @StringRes val description: Int
)