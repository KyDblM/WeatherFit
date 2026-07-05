package com.example.weatherfit.domain.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Mannequin(
    val warmIndex: Double,
    @DrawableRes val maleImage: Int,
    @DrawableRes val femaleImage: Int,
    var baseAccessories: MutableList<AccessoriesRepository> = mutableListOf()
)