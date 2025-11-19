package com.example.weatherfit.domain.usecase

import com.example.weatherfit.R
import com.example.weatherfit.domain.util.ActivityMode
import com.example.weatherfit.domain.util.AnswerOption
import com.example.weatherfit.domain.util.LocationType
import com.example.weatherfit.domain.util.QuestionSubject
import com.example.weatherfit.domain.model.WeatherData
import com.example.weatherfit.domain.util.AccessoriesRepository
import com.example.weatherfit.domain.util.Mannequin
import com.example.weatherfit.domain.util.MannequinsRepository

class GetFitSuggestion {
    fun execute(
        weather: WeatherData,
        surveyAnswers: Map<QuestionSubject, AnswerOption>,
        userColdSensitivity: Float
    ): Mannequin {
        val hoursOutside = surveyAnswers[QuestionSubject.HOURS]?.getHours()!!
        val activity = (surveyAnswers[QuestionSubject.ACTIVITY_MODE] as AnswerOption.Activity).value.label
        val location = (surveyAnswers[QuestionSubject.LOCATION] as AnswerOption.Location).value.label

        val effectiveTemp = (weather.forecast.forecastday[0].day.avgtemp_c + weather.forecast.forecastday[0].day.avgtemp_c / 2).coerceIn(-60.0, 40.0)

        val baseWarmth = when {
            effectiveTemp >= 25.0 -> 0.15
            effectiveTemp >= 20.0 -> 0.25
            effectiveTemp >= 0.0  -> 0.5 + 0.02 * (20.0 - effectiveTemp)
            else -> 0.9 + 0.05 * (0.0 - effectiveTemp)
        }

        var requiredWarmth = baseWarmth.coerceAtLeast(0.10)

        if (activity == ActivityMode.SPORT.label) {
            requiredWarmth -= 0.25
        }

        val sensitivityMultiplier = 1.0 + (userColdSensitivity - 0.5) * 0.6
        requiredWarmth *= sensitivityMultiplier

        if (weather.forecast.forecastday[0].day.daily_will_it_rain == 1 ||
            weather.forecast.forecastday[0].day.daily_will_it_snow == 1 ||
            weather.forecast.forecastday[1].day.daily_will_it_rain == 1 ||
            weather.forecast.forecastday[1].day.daily_will_it_snow == 1) {
            requiredWarmth += 0.25
        }

        if (weather.windSpeed > 5.0) {
            requiredWarmth += minOf(0.3, 0.05 * (weather.windSpeed - 5.0))
        }

        var shortColdAdjustment = 0.0
        var longHeatAdjustment: Double
        if (effectiveTemp < 10.0) {
            if (hoursOutside <= 2) shortColdAdjustment = 0.25
        }
        else if (effectiveTemp > 15.0) {
            longHeatAdjustment = minOf(0.25, 0.05 * (hoursOutside - 1))
            requiredWarmth -= longHeatAdjustment
        }

        val locationMultiplier = if (location == LocationType.OUTDOOR.label) 1.15 else 1.0
        requiredWarmth *= locationMultiplier

        val adjustedWarmth = (requiredWarmth - shortColdAdjustment).coerceAtLeast(0.0)

        val selectedClothing = MannequinsRepository().mannequins.firstOrNull { it.warmIndex >= adjustedWarmth } ?: MannequinsRepository().mannequins.last()

        val accessories = mutableListOf<AccessoriesRepository>()

        if (weather.weatherIcon == R.drawable.weather_sun_icon) {
            accessories.add(AccessoriesRepository.CAP)
            accessories.add(AccessoriesRepository.SUNGLASSES)
        }

        if (weather.uvIndex > 3) {
            accessories.add(AccessoriesRepository.SUNSCREEN)
        }

        if (weather.forecast.forecastday[0].day.daily_will_it_rain == 1 ||
            weather.forecast.forecastday[1].day.daily_will_it_rain == 1) {
            accessories.add(AccessoriesRepository.UMBRELLA)
        }

        selectedClothing.baseAccessories.addAll(accessories)

        return selectedClothing
    }
}