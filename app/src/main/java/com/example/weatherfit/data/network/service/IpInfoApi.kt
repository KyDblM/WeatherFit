package com.example.weatherfit.data.network.service

import com.example.weatherfit.data.network.model.ip.IpInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface IpInfoApi {
    @GET("ipgeo")
    suspend fun getIpInfo(
        @Query("apiKey") key: String
    ) : IpInfo
}