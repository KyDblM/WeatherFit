package com.example.weatherfit.data.util

import com.example.weatherfit.R

private val cloudyCodesList = listOf(1003, 1006, 1009, 1030, 1135, 1147)
private val rainCodesList = listOf(1063, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1189, 1192, 1195, 1240, 1243, 1246)
private val thunderCodesList = listOf(1087, 1273, 1276, 1279, 1282)
private val snowCodesList = listOf(1066, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258)
private val sleetCodesList = listOf(1069, 1072, 1198, 1201, 1204, 1207, 1249, 1252)
private val hailCodesList = listOf(1237, 1261, 1264)

fun chooseWeatherIcon(code: Int, isDay: Boolean): Int {
    return if (cloudyCodesList.any {it == code}) {
        R.drawable.weather_cloud_icon
    }
    else if (rainCodesList.any {it == code}) {
        R.drawable.weather_rain_icon
    }
    else if (thunderCodesList.any {it == code}) {
        R.drawable.weather_thunder_icon
    }
    else if (snowCodesList.any {it == code}) {
        R.drawable.weather_snow_icon
    }
    else if (sleetCodesList.any {it == code}) {
        R.drawable.weather_sleet_icon
    }
    else if (hailCodesList.any {it == code}) {
        R.drawable.weather_hail_icon
    }
    else {
        if (isDay) {
            R.drawable.weather_sun_icon
        } else {
            R.drawable.weather_moon_icon
        }
    }
}