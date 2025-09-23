package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.model.UserSettings
import com.example.weatherfit.domain.repository.UserSettingsRepository

class SaveSettings (private val userSettingsRepository: UserSettingsRepository) {
    fun execute(userSettings: UserSettings) {
        userSettingsRepository.saveSettings(userSettings)
    }
}