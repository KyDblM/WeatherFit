package com.example.weatherfit.data.repository

import android.content.Context
import com.example.weatherfit.domain.model.UserSettings
import com.example.weatherfit.domain.repository.UserSettingsRepository
import androidx.core.content.edit
import com.example.weatherfit.domain.model.AppTheme

private const val MANNEQUIN_TYPE_KEY = "mannequin_type"
private const val THEME_KEY = "app_theme"

class UserSettingsRepositoryImpl(val context: Context) : UserSettingsRepository {
    private val sharedPreferences = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE)

    override fun saveSettings(userSettings: UserSettings) {
        sharedPreferences.edit { putString(MANNEQUIN_TYPE_KEY, userSettings.mannequinType) }

        sharedPreferences.edit { putString(THEME_KEY, userSettings.theme) }
    }

    override fun checkSettingsExist(): Boolean {
        return sharedPreferences.contains(MANNEQUIN_TYPE_KEY)
    }

    override fun getAppTheme(): AppTheme? {
        if (sharedPreferences.contains(THEME_KEY)) {
            val theme = sharedPreferences.getString(THEME_KEY, "")

            if (theme == context.getString(AppTheme.LIGHT.label)) {
                return AppTheme.LIGHT
            }
            else if (theme == context.getString(AppTheme.DARK.label)) {
                return AppTheme.DARK
            }
        }

        return null
    }
}