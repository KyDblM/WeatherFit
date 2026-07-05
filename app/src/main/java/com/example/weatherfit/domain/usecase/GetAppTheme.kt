package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.util.AppTheme
import com.example.weatherfit.domain.repository.UserSettingsRepository

class GetAppTheme (private val userSettingsRepository: UserSettingsRepository) {
    fun execute() : AppTheme? {
        return userSettingsRepository.getAppTheme()
    }
}