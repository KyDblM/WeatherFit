package com.example.weatherfit.data.repository

import com.example.weatherfit.BuildConfig
import com.example.weatherfit.data.network.service.IpInfoApi
import com.example.weatherfit.domain.model.LocationCoordinates
import com.example.weatherfit.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: IpInfoApi
) : LocationRepository {
    override suspend fun getLocation(): LocationCoordinates {
        val info = api.getIpInfo(BuildConfig.IP_API_KEY)

        return LocationCoordinates(info.location.latitude, info.location.longitude)
    }
}