package com.example.weatherfit.di

import com.example.weatherfit.domain.repository.CurrentSuggestionRepository
import com.example.weatherfit.domain.repository.LocationRepository
import com.example.weatherfit.domain.repository.UserSettingsRepository
import com.example.weatherfit.domain.repository.WeatherDataRepository
import com.example.weatherfit.domain.usecase.CheckSettingsExist
import com.example.weatherfit.domain.usecase.GetAppTheme
import com.example.weatherfit.domain.usecase.GetColdSensitivity
import com.example.weatherfit.domain.usecase.GetCurrentSuggestion
import com.example.weatherfit.domain.usecase.GetFitSuggestion
import com.example.weatherfit.domain.usecase.GetLocationFromIp
import com.example.weatherfit.domain.usecase.GetWeather
import com.example.weatherfit.domain.usecase.SaveCurrentSuggestion
import com.example.weatherfit.domain.usecase.SaveSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSaveSettingsUseCase(userSettingsRepository: UserSettingsRepository): SaveSettings {
        return SaveSettings(userSettingsRepository)
    }

    @Provides
    fun provideCheckSettingsExistUseCase(userSettingsRepository: UserSettingsRepository): CheckSettingsExist {
        return CheckSettingsExist(userSettingsRepository)
    }

    @Provides
    fun provideGetAppThemeUseCase(userSettingsRepository: UserSettingsRepository): GetAppTheme {
        return GetAppTheme(userSettingsRepository)
    }

    @Provides
    fun provideGetColdSensitivityUseCase(userSettingsRepository: UserSettingsRepository): GetColdSensitivity {
        return GetColdSensitivity(userSettingsRepository)
    }

    @Provides
    fun provideGetLocationFromIpUseCase(locationRepository: LocationRepository): GetLocationFromIp {
        return GetLocationFromIp(locationRepository)
    }

    @Provides
    fun provideGetWeatherUseCase(weatherDataRepository: WeatherDataRepository): GetWeather {
        return GetWeather(weatherDataRepository)
    }

    @Provides
    fun provideGetFitSuggestionUseCase(): GetFitSuggestion {
        return GetFitSuggestion()
    }

    @Provides
    fun provideSaveCurrentSuggestionUseCase(currentSuggestionRepository: CurrentSuggestionRepository) : SaveCurrentSuggestion {
        return SaveCurrentSuggestion(currentSuggestionRepository)
    }

    @Provides
    fun provideGetCurrentSuggestionUseCase(currentSuggestionRepository: CurrentSuggestionRepository) : GetCurrentSuggestion {
        return GetCurrentSuggestion(currentSuggestionRepository)
    }
}