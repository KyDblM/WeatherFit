package com.example.weatherfit.presentation

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.weatherfit.domain.usecase.GetFitSuggestion
import com.example.weatherfit.domain.usecase.GetLocationFromIp
import com.example.weatherfit.domain.usecase.GetSuggestionsFromDb
import com.example.weatherfit.domain.usecase.GetWeather
import com.example.weatherfit.domain.usecase.SaveSettings
import com.example.weatherfit.domain.usecase.SaveSuggestionInDB
import com.example.weatherfit.domain.usecase.UpdateSuggestionInDb
import com.example.weatherfit.presentation.navigation.NavigationItem
import com.example.weatherfit.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val saveSuggestion: SaveSuggestionInDB,
    private val deleteSuggestion: DeleteSuggestionFromDB,
    private val deleteSuggestions: DeleteSuggestionsFromDB,
    private val getSuggestions: GetSuggestionsFromDb,
    private val updateSuggestion: UpdateSuggestionInDb
) : ViewModel() {
    val isDarkTheme: MutableState<Boolean?> = mutableStateOf(
        when (getAppTheme()) {
            AppTheme.DARK -> true
            AppTheme.LIGHT -> false
            else -> null
        }
    )

    val location: MutableState<String> = mutableStateOf("")
    val weather: MutableState<WeatherData?> = mutableStateOf(null)
    var surveyAnswers: Map<QuestionSubject, AnswerOption>? = null
    var selectedSuggestion: FitSuggestion? = null

    val suggestion = mutableStateOf<FitSuggestion?>(null)
    val suggestionsHistory = mutableStateOf<List<FitSuggestion>>(emptyList())

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

    init {
        viewModelScope.launch {
            suggestion.value = getCurrentSuggestionFromDatabase()
        }
    }

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
        if (surveyAnswers != null && weather.value != null) {
            suggestion.value = FitSuggestion(
                time = LocalDateTime.now().toString(),
                lifetime = surveyAnswers!![QuestionSubject.HOURS]?.getHours()!!,
                mannequin = getFitSuggestion.execute(
                    weather = weather.value!!,
                    surveyAnswers = surveyAnswers!!,
                    userColdSensitivity = getColdSensitivity.execute()
                ),
                weatherIcon = weather.value!!.weatherIcon,
                temperature = weather.value!!.temperature
            )
        }
    }

    fun saveSuggestionInDatabase() {
        if (suggestion.value != null) {
            CoroutineScope(Dispatchers.IO).launch {
                saveSuggestion.execute(suggestion.value!!)
            }
        }
    }

    fun getSuggestionsFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            suggestionsHistory.value = getSuggestions.execute()
        }
    }

    suspend fun getCurrentSuggestionFromDatabase(): FitSuggestion? {
        val currentSuggestion = withContext(Dispatchers.IO) {
            getSuggestions.execute().firstOrNull()
        }

        if (currentSuggestion != null) {
            if (Duration.between(LocalDateTime.parse(currentSuggestion.time), LocalDateTime.now()).toHours() >= currentSuggestion.lifetime) {
                return null
            }
        }

        return currentSuggestion
    }

    fun deleteSuggestion(suggestion: FitSuggestion) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteSuggestion.execute(suggestion)
        }
    }

    fun deleteSuggestions(suggestions: List<FitSuggestion>) {
        if (suggestions.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                deleteSuggestions.execute(suggestions)
                suggestion.value = getCurrentSuggestionFromDatabase()
                suggestionsHistory.value = getSuggestions.execute()
            }
        }
    }

    fun updateSuggestion(suggestion: FitSuggestion) {
        CoroutineScope(Dispatchers.IO).launch {
            updateSuggestion.execute(suggestion)
        }
    }
}