package com.example.weatherfit.data.network.model.ip

data class Location(
    val city: String,
    val continent_code: String,
    val continent_name: String,
    val country_capital: String,
    val country_code2: String,
    val country_code3: String,
    val country_emoji: String,
    val country_flag: String,
    val country_name: String,
    val country_name_official: String,
    val district: String,
    val geoname_id: String,
    val is_eu: Boolean,
    val latitude: String,
    val longitude: String,
    val state_code: String,
    val state_prov: String,
    val zipcode: String
)