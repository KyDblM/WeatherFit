package com.example.weatherfit.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherfit.R
import com.example.weatherfit.domain.util.QuestionsRepository
import com.example.weatherfit.presentation.components.CustomNavigationBar
import com.example.weatherfit.presentation.navigation.NavigationRoutes
import com.example.weatherfit.presentation.screens.HistoryScreen
import com.example.weatherfit.presentation.screens.HomeScreen
import com.example.weatherfit.presentation.screens.ProfileScreen
import com.example.weatherfit.presentation.screens.RegistrationScreen
import com.example.weatherfit.presentation.screens.SuggestionScreen
import com.example.weatherfit.presentation.screens.SurveyScreen
import com.example.weatherfit.presentation.theme.WeatherFitTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        window.isNavigationBarContrastEnforced = false
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )

        tryToGetLocation()

        setContent {
            LaunchedEffect(viewModel.location.value) {
                viewModel.location.value.let {
                    viewModel.getWeather(viewModel.location)
                }
            }

            WeatherFitTheme (viewModel.isDarkTheme.value) {
                val view = LocalView.current
                val window = (view.context as? Activity)?.window
                val isDark = viewModel.isDarkTheme.value ?: isSystemInDarkTheme()
                SideEffect {
                    window?.let { window ->
                        val controller = WindowCompat.getInsetsController(window, view)
                        controller.isAppearanceLightStatusBars = !isDark
                        controller.isAppearanceLightNavigationBars = !isDark
                    }
                }

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen = navBackStackEntry?.destination?.route ?: viewModel.startScreen

                Scaffold(
                    bottomBar = {
                        if (viewModel.navigationItems.any {it.route == currentScreen}) {
                            CustomNavigationBar(
                                navController = navController,
                                navigationItems = viewModel.navigationItems,
                                currentScreen = currentScreen,
                                navigationBarPadding = WindowInsets
                                    .navigationBars
                                    .only(WindowInsetsSides.Bottom)
                                    .asPaddingValues()
                                    .calculateBottomPadding()
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = viewModel.startScreen,
                        enterTransition = { fadeIn(animationSpec = tween(delayMillis = 1, durationMillis = 1)) },
                        exitTransition = { fadeOut(animationSpec = tween(durationMillis = 1)) },
                        popEnterTransition = { fadeIn(animationSpec = tween(delayMillis = 1, durationMillis = 1)) },
                        popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 1)) }
                    ) {
                        composable(route = NavigationRoutes.Registration.route) {
                            RegistrationScreen(
                                paddingValues = paddingValues,
                                questions = QuestionsRepository().registrationQuestions,
                                onFinished = { answers ->
                                    viewModel.saveSettings(answers)
                                    viewModel.updateAppTheme(viewModel.getAppTheme())

                                    navController.navigate(NavigationRoutes.Home.route)
                                }
                            )
                        }

                        composable(route = NavigationRoutes.Home.route) {
                            HomeScreen(
                                paddingValues = paddingValues,
                                weatherData = viewModel.weather,
                                suggestion = viewModel.suggestion,
                                onMannequinClick = {
                                    navController.navigate(NavigationRoutes.Survey.route)
                                },
                                onRetryClick = {
                                    tryToGetLocation()
                                }
                            )
                        }

                        composable(route = NavigationRoutes.Survey.route) {
                            SurveyScreen(
                                paddingValues = paddingValues,
                                questions = QuestionsRepository().surveyQuestions,
                                onFinished = { answers ->
                                    viewModel.surveyAnswers = answers
                                    viewModel.getSuggestion()
                                    viewModel.saveSuggestionInDatabase()

                                    navController.navigate(NavigationRoutes.Home.route)
                                },
                                onClose = {
                                    navController.navigate(NavigationRoutes.Home.route)
                                }
                            )
                        }

                        composable(route = NavigationRoutes.History.route) {
                            viewModel.getSuggestionsFromDatabase()

                            HistoryScreen(
                                paddingValues = paddingValues,
                                suggestions = viewModel.suggestionsHistory,
                                onSuggestionClick = { suggestion ->
                                    viewModel.selectedSuggestion = suggestion
                                    navController.navigate(NavigationRoutes.Suggestion.route)
                                },
                                onFeedbackClick = { suggestion ->
                                    viewModel.updateSuggestion(suggestion)
                                },
                                onDeleteSuggestionsClick = { suggestions ->
                                    viewModel.deleteSuggestions(suggestions)
                                }
                            )
                        }

                        composable(route = NavigationRoutes.Suggestion.route) {
                            if (viewModel.selectedSuggestion != null) {
                                SuggestionScreen(
                                    paddingValues = paddingValues,
                                    suggestion = viewModel.selectedSuggestion!!,
                                    onClose = {
                                        navController.navigate(NavigationRoutes.History.route)
                                        viewModel.selectedSuggestion = null
                                    },
                                    onDelete = { suggestion ->
                                        navController.navigate(NavigationRoutes.History.route)
                                        viewModel.deleteSuggestion(suggestion)
                                        viewModel.selectedSuggestion = null
                                    },
                                    onFeedbackClick = { suggestion ->
                                        viewModel.updateSuggestion(suggestion)
                                    }
                                )
                            }
                        }

                        composable(route = NavigationRoutes.Profile.route) {
                            val currentTheme = viewModel.getAppTheme()
                            val currentMannequinGender = viewModel.getMannequinGender()

                            if (currentTheme != null && currentMannequinGender != null) {
                                ProfileScreen(
                                    paddingValues = paddingValues,
                                    currentTheme = currentTheme,
                                    currentMannequinGender = currentMannequinGender,
                                    onThemeChange = {
                                        Toast.makeText(application, "Смена темы", Toast.LENGTH_LONG).show()
                                    },
                                    onMannequinTypeChange = {
                                        Toast.makeText(application, "Смена манекена", Toast.LENGTH_LONG).show()
                                    },
                                    onColdSensitivityReset = {
                                        Toast.makeText(application, "Сброс коэффициента", Toast.LENGTH_LONG).show()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    private fun tryToGetLocation() {
        if (isInternetAvailable()) {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getLocation()
            }
        }
        else {
            Toast.makeText(this, R.string.internet_error_text, Toast.LENGTH_LONG).show()
        }
    }
}