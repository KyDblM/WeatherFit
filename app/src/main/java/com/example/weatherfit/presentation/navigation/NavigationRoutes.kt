package com.example.weatherfit.presentation.navigation

sealed class NavigationRoutes(val route: String) {
    object Registration : NavigationRoutes("registration_screen")
    object Home : NavigationRoutes("home_screen")
    object History : NavigationRoutes("history_screen")
    object Profile : NavigationRoutes("profile_screen")
}