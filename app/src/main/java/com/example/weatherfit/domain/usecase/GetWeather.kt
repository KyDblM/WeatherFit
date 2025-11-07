package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.WeatherData
import com.example.weatherfit.domain.repository.WeatherDataRepository

class GetWeather(private val weatherDataRepository: WeatherDataRepository) {
    suspend fun execute(location: String): WeatherData {
        return weatherDataRepository.getWeather(location)
    }
}