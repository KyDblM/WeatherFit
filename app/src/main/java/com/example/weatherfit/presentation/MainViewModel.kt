package com.example.weatherfit.presentation

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weatherfit.R
import com.example.weatherfit.domain.util.AnswerOption
import com.example.weatherfit.domain.util.AppTheme
import com.example.weatherfit.domain.util.QuestionSubject
import com.example.weatherfit.domain.model.WeatherData
import com.example.weatherfit.domain.mapper.mapRegAnswersToUserSettings
import com.example.weatherfit.domain.model.FitSuggestion
import com.example.weatherfit.domain.usecase.CheckSettingsExist
import com.example.weatherfit.domain.usecase.DeleteSuggestionFromDB
import com.example.weatherfit.domain.usecase.DeleteSuggestionsFromDB
import com.example.weatherfit.domain.usecase.GetAppTheme
import com.example.weatherfit.domain.usecase.GetColdSensitivity
import com.example.weatherfit.domain.usecase.GetCurrentSuggestion
import com.example.weatherfit.domain.usecase.GetFitSuggestion
import com.example.weatherfit.domain.usecase.GetLocationFromIp
import com.example.weatherfit.domain.usecase.GetSuggestionsFromDb
import com.example.weatherfit.domain.usecase.GetWeather
import com.example.weatherfit.domain.usecase.SaveCurrentSuggestion
import com.example.weatherfit.domain.usecase.SaveSettings
import com.example.weatherfit.domain.usecase.SaveSuggestionInDB
import com.example.weatherfit.domain.util.Mannequin
import com.example.weatherfit.presentation.navigation.NavigationItem
import com.example.weatherfit.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.Duration
import java.time.LocalDateTime

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val saveSettingsUseCase: SaveSettings,
    private val checkSettingsExistUseCase: CheckSettingsExist,
    private val getAppThemeUseCase: GetAppTheme,
    private val getLocationFromIpUseCase: GetLocationFromIp,
    private val getWeatherUseCase: GetWeather,
    private val getFitSuggestion: GetFitSuggestion,
    private val getColdSensitivity: GetColdSensitivity,
    private val saveCurrentSuggestion: SaveCurrentSuggestion,
    private val getCurrentSuggestion: GetCurrentSuggestion,
    private val saveSuggestion: SaveSuggestionInDB,
    private val deleteSuggestion: DeleteSuggestionFromDB,
    private val deleteSuggestions: DeleteSuggestionsFromDB,
    private val getSuggestions: GetSuggestionsFromDb
) : ViewModel() {
    val isDarkTheme: MutableState<Boolean?> = mutableStateOf(
        when (getAppTheme()) {
            AppTheme.DARK -> true
            AppTheme.LIGHT -> false
            else -> null
        }
    )

    val currentSuggestion: FitSuggestion? = getCurrentSuggestionFromSharedPreferences()

    val location: MutableState<String> = mutableStateOf("")
    val weather: MutableState<WeatherData?> = mutableStateOf(null)
    var suggestion: MutableState<Mannequin?> = mutableStateOf(currentSuggestion?.mannequin)

    var surveyAnswers: Map<QuestionSubject, AnswerOption>? = null

    val navigationItems = listOf(
        NavigationItem(
            title = context.getString(R.string.navigation_title_profile),
            image = R.drawable.navigation_profile_icon,
            route = NavigationRoutes.Profile.route
        ),
        NavigationItem(
            title = context.getString(R.string.navigation_title_home),
            image = R.drawable.navigation_home_icon,
            route = NavigationRoutes.Home.route
        ),
        NavigationItem(
            title = context.getString(R.string.navigation_title_history),
            image = R.drawable.navigation_history_icon,
            route = NavigationRoutes.History.route
        )
    )

    val startScreen = if (checkSettingsExist()) "home_screen" else "registration_screen"

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

    suspend fun getLocation() {
        val locationFromIp = getLocationFromIpUseCase.execute()
        location.value = locationFromIp.latitude + "," + locationFromIp.longitude
    }

    fun getWeather(location: MutableState<String>) {
        if (location.value != "") {
            CoroutineScope(Dispatchers.IO).launch {
                weather.value = getWeatherUseCase.execute(location.value)
            }
        }
    }

    fun getSuggestion() {
        suggestion.value = getFitSuggestion.execute(
            weather = weather.value!!,
            surveyAnswers = surveyAnswers!!,
            userColdSensitivity = getColdSensitivity.execute()
        )
    }

    fun saveCurrentSuggestionToSharedPreferences() {
        if (suggestion.value != null) {
            saveCurrentSuggestion.execute(
                FitSuggestion(
                    time = LocalDateTime.now().toString(),
                    lifetime = surveyAnswers!![QuestionSubject.HOURS]?.getHours()!!,
                    mannequin = suggestion.value!!,
                    weatherIcon = weather.value!!.weatherIcon,
                    temperature = weather.value!!.temperature
                )
            )
        }
    }

    fun getCurrentSuggestionFromSharedPreferences() : FitSuggestion? {
        val sharedPreferencesSuggestion = getCurrentSuggestion.execute()

        return if (sharedPreferencesSuggestion != null) {
            if (Duration.between(
                    LocalDateTime.parse(sharedPreferencesSuggestion.time), LocalDateTime.now()
                ).toHours() >= sharedPreferencesSuggestion.lifetime) {
                null
            }
            else {
                sharedPreferencesSuggestion
            }
        }
        else {
            null
        }
    }

    fun saveSuggestionInDatabase() {
        if (currentSuggestion != null) {
            CoroutineScope(Dispatchers.IO).launch {
                saveSuggestion.execute(currentSuggestion)
            }
        }
    }
}