package com.example.weatherfit.data.network.model.ip

data class IpInfo(
    val country_metadata: CountryMetadata,
    val currency: Currency,
    val ip: String,
    val location: Location
)