package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.repository.UserSettingsRepository

class GetColdSensitivity (private val userSettingsRepository: UserSettingsRepository)  {
    fun execute() : Float {
        return userSettingsRepository.getColdSensitivity()
    }
}