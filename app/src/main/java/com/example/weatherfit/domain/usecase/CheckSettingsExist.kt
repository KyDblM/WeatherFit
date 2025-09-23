package com.example.weatherfit.domain.usecase

import com.example.weatherfit.domain.repository.UserSettingsRepository

class CheckSettingsExist (private val userSettingsRepository: UserSettingsRepository) {
    fun execute() : Boolean {
        return userSettingsRepository.checkSettingsExist()
    }
}