package com.example.weatherfit.presentation

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherfit.R
import com.example.weatherfit.domain.model.AnswerOption
import com.example.weatherfit.domain.model.AppTheme
import com.example.weatherfit.domain.model.QuestionSubject
import com.example.weatherfit.domain.model.mapper.mapRegAnswersToUserSettings
import com.example.weatherfit.domain.usecase.CheckSettingsExist
import com.example.weatherfit.domain.usecase.GetAppTheme
import com.example.weatherfit.domain.usecase.SaveSettings
import com.example.weatherfit.presentation.navigation.NavigationItem
import com.example.weatherfit.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val saveSettingsUseCase: SaveSettings,
    private val checkSettingsExistUseCase: CheckSettingsExist,
    private val getAppThemeUseCase: GetAppTheme
) : ViewModel() {
    val isDarkTheme: MutableState<Boolean?> = mutableStateOf(
        value = when (getAppTheme()) {
            AppTheme.DARK -> true
            AppTheme.LIGHT -> false
            else -> null
        }
    )

    val navigationItems = listOf(
        NavigationItem(
            title = context.getString(R.string.navigation_title_home),
            image = R.drawable.navigation_home_icon,
            route = NavigationRoutes.Home.route
        ),
        NavigationItem(
            title = context.getString(R.string.navigation_title_history),
            image = R.drawable.navigation_history_icon,
            route = NavigationRoutes.History.route
        ),
        NavigationItem(
            title = context.getString(R.string.navigation_title_profile),
            image = R.drawable.navigation_profile_icon,
            route = NavigationRoutes.Profile.route
        )
    )

    val currentScreen: MutableState<String> = mutableStateOf(if (checkSettingsExist()) "home_screen" else "registration_screen")

    fun saveSettings(settings: Map<QuestionSubject, AnswerOption>) {
        val userSettings = mapRegAnswersToUserSettings(settings, context)

        saveSettingsUseCase.execute(userSettings)
    }

    fun checkSettingsExist(): Boolean {
        return checkSettingsExistUseCase.execute()
    }

    fun getAppTheme() : AppTheme? {
        return getAppThemeUseCase.execute()
    }

    fun updateAppTheme(appTheme: AppTheme?) {
        isDarkTheme.value = when (appTheme) {
            AppTheme.DARK -> true
            AppTheme.LIGHT -> false
            else -> null
        }
    }
}