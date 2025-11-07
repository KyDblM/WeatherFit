package com.example.weatherfit.presentation.navigation

import androidx.annotation.DrawableRes

data class NavigationItem(
    val title: String,
    @DrawableRes val image: Int,
    val route: String
)
