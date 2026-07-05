package com.example.weatherfit.domain.model

import com.example.weatherfit.data.network.model.weather.Forecast

data class WeatherData(
    val city: String,
    val weatherIcon: Int,
    val temperature: Int,
    val windSpeed: Int,
    val humidity: Int,
    val uvIndex: Int,
    val rainChance: Int,
    val forecast: Forecast
)