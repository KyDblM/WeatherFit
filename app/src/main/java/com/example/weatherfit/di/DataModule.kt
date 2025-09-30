package com.example.weatherfit.di

import android.content.Context
import com.example.weatherfit.data.repository.UserSettingsRepositoryImpl
import com.example.weatherfit.domain.repository.UserSettingsRepository
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
}