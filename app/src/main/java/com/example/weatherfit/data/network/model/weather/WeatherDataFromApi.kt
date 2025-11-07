package com.example.weatherfit.data.network.model.weather

data class WeatherDataFromApi(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)