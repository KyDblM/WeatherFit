package com.example.weatherfit.data.mapper

import com.example.weatherfit.data.network.model.weather.WeatherDataFromApi
import com.example.weatherfit.data.util.chooseWeatherIcon
import com.example.weatherfit.domain.model.WeatherData
import kotlin.math.roundToInt

class WeatherDataApiToDomainMapper() {
    fun map(weatherDataApi: WeatherDataFromApi) : WeatherData {
        return WeatherData(
            city = weatherDataApi.location.name,
            weatherIcon = chooseWeatherIcon(
                code = weatherDataApi.current.condition.code,
                isDay = weatherDataApi.current.is_day == 1
            ),
            temperature = weatherDataApi.current.temp_c.roundToInt(),
            windSpeed = (weatherDataApi.current.wind_kph / 3.6).roundToInt(),
            humidity = weatherDataApi.current.humidity,
            uvIndex = weatherDataApi.current.uv.roundToInt(),
            rainChance = if (weatherDataApi.forecast.forecastday[0].day.daily_chance_of_rain == 0) {
                weatherDataApi.forecast.forecastday[0].day.daily_chance_of_snow
            }
            else {
                weatherDataApi.forecast.forecastday[0].day.daily_chance_of_rain
            },
            forecast = weatherDataApi.forecast,
        )
    }
}