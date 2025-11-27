package com.example.weatherfit.di

import android.content.Context
import com.example.weatherfit.data.mapper.WeatherDataApiToDomainMapper
import com.example.weatherfit.data.network.service.IpInfoApi
import com.example.weatherfit.data.network.service.WeatherApi
import com.example.weatherfit.data.repository.CurrentSuggestionRepositoryImpl
import com.example.weatherfit.data.repository.LocationRepositoryImpl
import com.example.weatherfit.data.repository.UserSettingsRepositoryImpl
import com.example.weatherfit.data.repository.WeatherDataRepositoryImpl
import com.example.weatherfit.domain.repository.CurrentSuggestionRepository
import com.example.weatherfit.domain.repository.LocationRepository
import com.example.weatherfit.domain.repository.UserSettingsRepository
import com.example.weatherfit.domain.repository.WeatherDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideUserSettingsRepository(@ApplicationContext context: Context): UserSettingsRepository {
        return UserSettingsRepositoryImpl(context)
    }

    @Provides
    fun provideLocationRepository(api: IpInfoApi): LocationRepository {
        return LocationRepositoryImpl(api)
    }

    @Provides
    fun provideWeatherDataRepository(
        api: WeatherApi,
        mapper: WeatherDataApiToDomainMapper
    ): WeatherDataRepository {
        return WeatherDataRepositoryImpl(api, mapper)
    }

    @Provides
    fun provideWeatherDataMapper(): WeatherDataApiToDomainMapper {
        return WeatherDataApiToDomainMapper()
    }

    @Provides
    fun provideCurrentSuggestionRepository(@ApplicationContext context: Context): CurrentSuggestionRepository {
        return CurrentSuggestionRepositoryImpl(context)
    }
}