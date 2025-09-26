package com.example.weatherfit.di

import com.example.weatherfit.domain.model.mapper.mapRegAnswersToUserSettings
import com.example.weatherfit.domain.repository.UserSettingsRepository
import com.example.weatherfit.domain.usecase.CheckSettingsExist
import com.example.weatherfit.domain.usecase.GetAppTheme
import com.example.weatherfit.domain.usecase.SaveSettings
import dagger.Module
import dagger.Provides

@Module
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
}