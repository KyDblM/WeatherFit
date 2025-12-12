package com.example.weatherfit.domain.repository

import com.example.weatherfit.domain.util.AppTheme
import com.example.weatherfit.domain.model.UserSettings
import com.example.weatherfit.domain.util.MannequinGender

interface UserSettingsRepository {
    fun saveSettings(userSettings: UserSettings)

    fun editTheme(appTheme: AppTheme)

    fun editMannequinGender(mannequinGender: MannequinGender)

    fun editColdSensitivity(coldSensitivity: Float)

    fun checkSettingsExist() : Boolean

    fun getAppTheme() : AppTheme?

    fun getMannequinGender() : MannequinGender?

    fun getColdSensitivity() : Float
}