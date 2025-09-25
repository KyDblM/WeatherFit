package com.example.weatherfit.presentation

import androidx.lifecycle.ViewModel
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.QuestionSubject
import com.example.weatherfit.domain.model.UserSettings
import com.example.weatherfit.domain.model.mapper.mapRegAnswersToUserSettings
import com.example.weatherfit.domain.usecase.CheckSettingsExist
import com.example.weatherfit.domain.usecase.SaveSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveSettingsUseCase: SaveSettings,
    private val checkSettingsExistUseCase: CheckSettingsExist
) : ViewModel() {

    fun saveSettings(settings: Map<QuestionSubject, AnswerOption>) {
        val userSettings = mapRegAnswersToUserSettings(settings)

        saveSettingsUseCase.execute(userSettings)
    }

    fun checkSettingsExist(): Boolean {
        return checkSettingsExistUseCase.execute()
    }
}