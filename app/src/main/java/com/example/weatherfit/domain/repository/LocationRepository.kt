package com.example.weatherfit.domain.repository

import com.example.weatherfit.domain.model.LocationCoordinates

interface LocationRepository {
    suspend fun getLocation() : LocationCoordinates
}