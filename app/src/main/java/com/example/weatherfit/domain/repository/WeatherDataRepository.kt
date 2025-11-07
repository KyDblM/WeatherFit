package com.example.weatherfit.domain.repository

import com.example.weatherfit.domain.model.WeatherData

interface WeatherDataRepository {
    suspend fun getWeather(location: String): WeatherData
}