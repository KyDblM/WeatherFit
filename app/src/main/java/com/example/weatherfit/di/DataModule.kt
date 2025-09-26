package com.example.weatherfit.di

import android.content.Context
import com.example.weatherfit.data.repository.UserSettingsRepositoryImpl
import com.example.weatherfit.domain.repository.UserSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
class DataModule {

    @Provides
    fun provideUserSettingsRepository(@ApplicationContext context: Context): UserSettingsRepository {
        return UserSettingsRepositoryImpl(context)
    }
}