package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.LocationCoordinates
import com.example.weatherfit.domain.repository.LocationRepository

class GetLocationFromIp(private val locationRepository: LocationRepository) {
    suspend fun execute() : LocationCoordinates {
        return locationRepository.getLocation()
    }
}