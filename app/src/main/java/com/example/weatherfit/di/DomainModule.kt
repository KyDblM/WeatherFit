package com.example.weatherfit.di

import com.example.weatherfit.domain.repository.LocationRepository
import com.example.weatherfit.domain.repository.SuggestionsDbRepository
import com.example.weatherfit.domain.repository.UserSettingsRepository
import com.example.weatherfit.domain.repository.WeatherDataRepository
import com.example.weatherfit.domain.usecase.CheckSettingsExist
import com.example.weatherfit.domain.usecase.DeleteSuggestionFromDB
import com.example.weatherfit.domain.usecase.DeleteSuggestionsFromDB
import com.example.weatherfit.domain.usecase.GetAppTheme
import com.example.weatherfit.domain.usecase.GetColdSensitivity
import com.example.weatherfit.domain.usecase.GetFitSuggestion
import com.example.weatherfit.domain.usecase.GetLocationFromIp
import com.example.weatherfit.domain.usecase.GetSuggestionsFromDb
import com.example.weatherfit.domain.usecase.GetWeather
import com.example.weatherfit.domain.usecase.SaveSettings
import com.example.weatherfit.domain.usecase.SaveSuggestionInDB
import com.example.weatherfit.domain.usecase.UpdateSuggestionInDb
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
    fun provideSaveSuggestionInDbUseCase(suggestionsDbRepository: SuggestionsDbRepository) : SaveSuggestionInDB {
        return SaveSuggestionInDB(suggestionsDbRepository)
    }

    @Provides
    fun provideDeleteSuggestionFromDbUseCase(suggestionsDbRepository: SuggestionsDbRepository) : DeleteSuggestionFromDB {
        return DeleteSuggestionFromDB(suggestionsDbRepository)
    }

    @Provides
    fun provideDeleteSuggestionsFromDbUseCase(suggestionsDbRepository: SuggestionsDbRepository) : DeleteSuggestionsFromDB {
        return DeleteSuggestionsFromDB(suggestionsDbRepository)
    }

    @Provides
    fun provideGetSuggestionsFromDbUseCase(suggestionsDbRepository: SuggestionsDbRepository) : GetSuggestionsFromDb {
        return GetSuggestionsFromDb(suggestionsDbRepository)
    }

    @Provides
    fun provideUpdateSuggestionInDbUseCase(suggestionsDbRepository: SuggestionsDbRepository) : UpdateSuggestionInDb {
        return UpdateSuggestionInDb(suggestionsDbRepository)
    }
}