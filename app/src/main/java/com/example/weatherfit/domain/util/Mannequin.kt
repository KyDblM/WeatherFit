package com.example.weatherfit.domain.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Mannequin(
    val warmIndex: Double,
    @DrawableRes val image: Int,
    @StringRes val description: Int,
    var baseAccessories: MutableList<AccessoriesRepository> = mutableListOf()
)