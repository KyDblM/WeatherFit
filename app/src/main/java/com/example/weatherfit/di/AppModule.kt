package com.example.weatherfit.di

import com.example.weatherfit.data.network.service.IpInfoApi
import com.example.weatherfit.data.network.service.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Named("IpInfoRetrofit")
    fun provideIpInfoRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.ipgeolocation.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideIpInfoApi(@Named("IpInfoRetrofit") retrofit: Retrofit): IpInfoApi {
        return retrofit.create(IpInfoApi::class.java)
    }

    @Provides
    @Named("WeatherDataRetrofit")
    fun provideWeatherDataRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherApi(@Named("WeatherDataRetrofit") retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}