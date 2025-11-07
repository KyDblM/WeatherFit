package com.example.weatherfit.data.repository

import com.example.weatherfit.BuildConfig
import com.example.weatherfit.data.mapper.WeatherDataApiToDomainMapper
import com.example.weatherfit.data.network.service.WeatherApi
import com.example.weatherfit.domain.model.WeatherData
import com.example.weatherfit.domain.repository.WeatherDataRepository
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val mapper: WeatherDataApiToDomainMapper
): WeatherDataRepository {
    override suspend fun getWeather(location: String): WeatherData {
        val weather = api.getWeatherDataFromApi(
            key = BuildConfig.WEATHER_API_KEY,
            location = location
        )

        return mapper.map(weather)
    }
}