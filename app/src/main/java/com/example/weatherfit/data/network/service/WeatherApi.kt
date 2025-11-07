package com.example.weatherfit.data.network.service

import com.example.weatherfit.data.network.model.weather.WeatherDataFromApi
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeatherDataFromApi(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("days") days: String = "2",
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
    ) : WeatherDataFromApi
}