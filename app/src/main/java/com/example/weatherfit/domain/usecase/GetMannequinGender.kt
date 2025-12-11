package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.repository.UserSettingsRepository
import com.example.weatherfit.domain.util.AppTheme
import com.example.weatherfit.domain.util.Mannequin
import com.example.weatherfit.domain.util.MannequinGender

class GetMannequinGender (private val userSettingsRepository: UserSettingsRepository) {
    fun execute() : MannequinGender? {
        return userSettingsRepository.getMannequinGender()
    }
}