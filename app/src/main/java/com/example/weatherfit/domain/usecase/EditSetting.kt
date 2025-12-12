package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.repository.UserSettingsRepository
import com.example.weatherfit.domain.util.AppTheme
import com.example.weatherfit.domain.util.MannequinGender

class EditSetting(private val userSettingsRepository: UserSettingsRepository) {
    fun execute(appTheme: AppTheme) {
        userSettingsRepository.editTheme(appTheme)
    }

    fun execute(mannequinGender: MannequinGender) {
        userSettingsRepository.editMannequinGender(mannequinGender)
    }

    fun execute(coldSensitivity: Float) {
        userSettingsRepository.editColdSensitivity(coldSensitivity)
    }
}